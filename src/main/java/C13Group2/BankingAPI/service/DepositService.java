package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.enums.DepositStatus;
import C13Group2.BankingAPI.enums.TransactionType;
import C13Group2.BankingAPI.exceptions.ResourceNotFoundException;
import C13Group2.BankingAPI.model.Account;
import C13Group2.BankingAPI.model.Deposit;
import C13Group2.BankingAPI.repositories.BillRepository;
import C13Group2.BankingAPI.repositories.DepositRepository;
import C13Group2.BankingAPI.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

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
    public Deposit createDeposit(Long accountId, String medium, Double amount, String description){
        verifyIfAccountExists(accountId);
        Deposit deposit = new Deposit();
        deposit.setType(TransactionType.DEPOSIT);
        deposit.setStatus(DepositStatus.COMPLETED);
        deposit.setMedium(medium);
        deposit.setDescription(description);

        deposit.setAccount(accountRepository.findById(accountId).get());
        Account addAmount = accountRepository.findById(accountId).get();
        addAmount.setBalance(addAmount.getBalance() + deposit.getAmount());
        accountRepository.save(addAmount);


        return depositRepository.save(deposit);

    }
    public Deposit updateDeposit(Deposit deposit){
        return depositRepository.save(deposit);
    }
    public void deleteDeposit(Long depositId){
        depositRepository.deleteById(depositId);
    }
}
