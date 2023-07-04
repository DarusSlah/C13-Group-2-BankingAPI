package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.dto.CreateAccountDTO;
import C13Group2.BankingAPI.enums.AccountType;
import C13Group2.BankingAPI.exceptions.ResourceNotFoundException;
import C13Group2.BankingAPI.model.Account;



import C13Group2.BankingAPI.repositories.AccountRepository;
import C13Group2.BankingAPI.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private void verifyIfAccountExists(Long accountId, String exceptionMessage)throws ResourceNotFoundException {
        if(!(accountRepository.existsById(accountId))){
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }
    protected void verifyCustomer (Long customerId, String exceptionMessage) throws ResourceNotFoundException {
        if(!(this.customerRepository.existsById(customerId))) {
            throw (new ResourceNotFoundException(exceptionMessage));
        }
    }


    public Iterable<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    public Account getAccountById(Long accountId, String exceptionMessage){
        verifyIfAccountExists(accountId, exceptionMessage);
        return accountRepository.findById(accountId).orElse(null);
    }
    public Iterable<Account>getAllAccountsByCustomerId(Long customerId){
        return accountRepository.findAllAccountsByCustomerId(customerId);
    }

    public Account createAccount(Long customerId, String exceptionMessage, CreateAccountDTO createAccountDTO){
        this.verifyCustomer(customerId, exceptionMessage);
        Account account = new Account();

        String accountType = createAccountDTO.getType().trim().toUpperCase();
        try {
            account.setAccountType(AccountType.valueOf(accountType));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid account type: " + accountType);
        }

        account.setBalance(0.0);
        if (!(Objects.isNull(createAccountDTO.getNickname())) && !(createAccountDTO.getNickname().isBlank())) {
            account.setNickname(createAccountDTO.getNickname().trim());
        }
        account.setRewards(0);
        account.setCustomer(customerRepository.findById(customerId).get());
        return accountRepository.save(account);

    }

    public Account updateAccount(Long accountId,Account account,String exceptionMessage) {
        verifyIfAccountExists(accountId, exceptionMessage);
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

    public void deleteAccount(Long accountId, String exceptionMessage){
        verifyIfAccountExists(accountId, exceptionMessage);
        accountRepository.deleteById(accountId);
    }
}
