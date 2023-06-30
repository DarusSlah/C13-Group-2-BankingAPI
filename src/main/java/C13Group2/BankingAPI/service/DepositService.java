package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.enums.DepositStatus;
import C13Group2.BankingAPI.enums.TransactionType;
import C13Group2.BankingAPI.model.Account;
import C13Group2.BankingAPI.model.Deposit;
import C13Group2.BankingAPI.repositories.DepositRepository;
import C13Group2.BankingAPI.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;

    @Autowired
    AccountRepository accountRepository;

    public Iterable<Deposit> getAllDepositByAccountId(){
        return depositRepository.findAll();
    }
    public Deposit getDepositById(Long depositId){
        return depositRepository.findById(depositId).orElse(null);
    }
    public Deposit createDeposit(Long accountId, String medium, Double amount, String description){
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
