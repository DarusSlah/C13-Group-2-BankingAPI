package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.dto.CreateDepositDTO;
import C13Group2.BankingAPI.dto.UpdateDepositDTO;
import C13Group2.BankingAPI.enums.DepositStatus;
import C13Group2.BankingAPI.enums.Medium;
import C13Group2.BankingAPI.enums.TransactionType;
import C13Group2.BankingAPI.exceptions.ResourceNotFoundException;
import C13Group2.BankingAPI.model.Account;
import C13Group2.BankingAPI.model.Deposit;
import C13Group2.BankingAPI.repositories.BillRepository;
import C13Group2.BankingAPI.repositories.DepositRepository;
import C13Group2.BankingAPI.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BillRepository billRepository;

    private void verifyIfAccountExists(Long accountId)throws ResourceNotFoundException {
        if(!(accountRepository.existsById(accountId))){
            throw new ResourceNotFoundException("“error fetching bills " + accountId);
        }
    }
    protected void verifyDeposit(Long transactionId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.depositRepository.existsById(transactionId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
        if(depositRepository.findById(transactionId).get().getType() != TransactionType.DEPOSIT) {
            throw (new ResourceNotFoundException("Error : invalid ID "));
        }
    }

    public Iterable<Deposit> getAllDeposits() {
        List<Deposit> allDeposits = new ArrayList<>();
        for (Deposit deposit : depositRepository.findAll()) {
            if (deposit.getType() == TransactionType.DEPOSIT) {
                allDeposits.add(deposit);
            }
        }
        return allDeposits;
    }

    public Iterable<Deposit> getAllDepositByAccountId(Long accountId){
        verifyIfAccountExists(accountId);
        List<Deposit> allDepositsByAccountId = new ArrayList<>();
        for (Deposit deposit : depositRepository.findAllDepositByAccountId(accountId)) {
            if (deposit.getType() == TransactionType.DEPOSIT) {
                allDepositsByAccountId.add(deposit);
            }
        }
        return allDepositsByAccountId;
    }
    public Deposit getDepositById(Long depositId){
        return depositRepository.findById(depositId).orElse(null);
    }
    public Deposit createDeposit(Long accountId,CreateDepositDTO createDepositDTO){
        verifyIfAccountExists(accountId);
        LocalDate currentDate = LocalDate.now();
        Deposit deposit = new Deposit();
        deposit.setType(TransactionType.DEPOSIT);
        deposit.setStatus(DepositStatus.COMPLETED);
        deposit.setMedium(Medium.valueOf(String.valueOf(Medium.BALANCE)));
        deposit.setDescription(createDepositDTO.getDescription());
        deposit.setAmount(createDepositDTO.getAmount());
        deposit.setAccount(accountRepository.findById(accountId).get());
        deposit.setTransaction_date(currentDate);
        Account addAmount = accountRepository.findById(accountId).get();
        addAmount.setBalance(addAmount.getBalance() + deposit.getAmount());
        accountRepository.save(addAmount);


        return depositRepository.save(deposit);

    }
    public Deposit updateDeposit(Long depositId,String exceptionMessage, UpdateDepositDTO updateDepositDTO){
        verifyDeposit(depositId,exceptionMessage);
        Deposit changeDeposit = depositRepository.findById(depositId).orElse(null);
        changeDeposit.setDescription(updateDepositDTO.getDescription());

        return depositRepository.save(changeDeposit);
    }
    public Deposit deleteDeposit(Long depositId){
        Deposit deposit = depositRepository.findById(depositId).orElse(null);
        if (deposit != null) {
            depositRepository.deleteById(depositId);
            return deposit;
        } else {
            throw new EntityNotFoundException("Deposit with id " + depositId + " not found");
        }
    }
}
