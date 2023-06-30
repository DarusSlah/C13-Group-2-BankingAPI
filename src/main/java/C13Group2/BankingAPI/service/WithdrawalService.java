package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.enums.TransactionType;
import C13Group2.BankingAPI.enums.WithdrawalStatus;
import C13Group2.BankingAPI.model.Account;
import C13Group2.BankingAPI.model.Withdrawal;
import C13Group2.BankingAPI.repositories.AccountRepository;
import C13Group2.BankingAPI.repositories.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawalService {
    @Autowired
    WithdrawalRepository withdrawalRepository;

    @Autowired
    AccountRepository accountRepository;

    public Iterable<Withdrawal>getAllWithdrawals(){
        List<Withdrawal> withdrawalList = new ArrayList<>();
        for (Withdrawal withdrawal: withdrawalRepository.findAll()){
            if(withdrawal.getType() == TransactionType.WITHDRAWAL){
                withdrawalList.add(withdrawal);
            }
        }
        return withdrawalList;
    }

    public Withdrawal getWithdrawalById(Long withdrawalId){
        return withdrawalRepository.findById(withdrawalId).orElse(null);

    }

    public Withdrawal createWithdrawal(Long accountId, String medium, Double amount, String description ){
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setType(TransactionType.WITHDRAWAL);
        withdrawal.setStatus(WithdrawalStatus.COMPLETED);
        withdrawal.setMedium(medium);
        withdrawal.setAmount(amount);
        withdrawal.setDescription(description);

        // taking out money from the account
        withdrawal.setAccount(accountRepository.findById(accountId).orElse(null));
        Account withdrawalFunds = accountRepository.findById(accountId).orElse(null);
        withdrawalFunds.setBalance(withdrawalFunds.getBalance()- withdrawal.getAmount());
        accountRepository.save(withdrawalFunds);


        return withdrawalRepository.save(withdrawal);
    }
    public Withdrawal updateWithdrawal(Long withdrawalId,String newMedium, Double newAmount, String newDescription){
        Withdrawal existingWithdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        existingWithdrawal.setMedium(newMedium);
        existingWithdrawal.setAmount(newAmount);
        existingWithdrawal.setDescription(newDescription);

        return withdrawalRepository.save(existingWithdrawal);
    }

    public void deleteWithdrawal(Long withdrawalId){

         withdrawalRepository.deleteById(withdrawalId);
    }

}
