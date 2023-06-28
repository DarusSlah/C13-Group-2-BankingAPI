package C13Group2.BankingAPI.service;

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
    public Account createAccount(Account account){
        return accountRepository.save(account);
    }
    public Account updateAccount(Long id,Account account){
        account.setId(id);
        return accountRepository.save(account);
    }
    public void deleteAccount(Long accountId){
        accountRepository.deleteById(accountId);
    }
}
