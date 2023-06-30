package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.enums.AccountType;
import C13Group2.BankingAPI.model.Account;
import C13Group2.BankingAPI.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class AccountController {
    AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts(){
        return new ResponseEntity<>(accountService.getAllAccounts(),HttpStatus.OK);
    }
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId){
        return new ResponseEntity<>(accountService.getAccountById(accountId),HttpStatus.OK);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Void> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        accountService.updateAccount(id, account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestParam String nickname, @RequestParam AccountType accountType) {
        Account createdAccount = accountService.createAccount(nickname, accountType);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
