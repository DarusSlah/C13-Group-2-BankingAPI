package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.enums.AccountType;
import C13Group2.BankingAPI.model.Account;

import C13Group2.BankingAPI.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;


    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    public Account getAccountById(Long accountId){
        return accountRepository.findById(accountId).orElse(null);
    }
    public Iterable<Account>getAllAccountsByCustomerId(Long customerId){
        return accountRepository.findAllAccountsByCustomerId(customerId);
    }
    public Account createAccount(String Nickname, AccountType accountType){
        Account createdAccount = new Account();
        createdAccount.setAccountType(accountType);
        createdAccount.setNickname(Nickname);
        createdAccount.setBalance(0.0);
        createdAccount.setRewards(0);
        return accountRepository.save(createdAccount);
    }
    public Account updateAccount(Long accountId,Account account) {
        // Find the account by its ID.
        Account accountToUpdate = accountRepository.findById(accountId).get();
        // Check if the new nickname is not null and is not blank.
        if (account.getNickname() != null && !account.getNickname().isEmpty()) {
            // If it's not, update the nickname of the account.
            accountToUpdate.setNickname(account.getNickname().trim());
        }
        // Check if the new rewards value is not null.
        if (account.getRewards() != null) {
            // If it's not, update the rewards of the account.
            accountToUpdate.setRewards(account.getRewards());
        }
        // Save the updated account to the database and return it.
        return accountRepository.save(accountToUpdate);
    }

    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }
}
