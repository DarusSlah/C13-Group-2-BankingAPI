package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.enums.AccountType;
import C13Group2.BankingAPI.model.Account;
import C13Group2.BankingAPI.model.Bill;
import C13Group2.BankingAPI.response.SuccessResponse;
import C13Group2.BankingAPI.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.chrono.IsoChronology;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<?> getAllAccounts(){
        int code = HttpStatus.OK.value();
        String messages = "Successfully fetched all accounts";
        Iterable<Account> data = accountService.getAllAccounts();
        SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages,data);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId){
        String exceptionMessage = "Unable to fetch account as no account was found matching the provided account ID: " + accountId;
        int code = HttpStatus.OK.value();
        String messages = "Successfully fetched account matching the provided account ID: " + accountId;
        Account data = accountService.getAccountById(accountId,exceptionMessage);
        SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages,data);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @GetMapping()

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
        String exceptionMessage = "Unable to update account as no account was found matching the provided account ID: " + accountId;
        int code = HttpStatus.OK.value();
        String messages = "Update Successful";
        Account data = accountService.updateAccount(accountId, account,exceptionMessage);
        SuccessResponse<Account> successResponse = new SuccessResponse<> (code,messages,data);
        return new ResponseEntity<>(successResponse,HttpStatus.CREATED);
    }


    @PostMapping("/customers/{customerId}/accounts")
    public ResponseEntity<Account> createAccount(@PathVariable Long customerId,@Valid @RequestBody Account account) {
        String exceptionMessage = "Unable to create new account as no customer was found matching the provided customer ID";
        Account createdAccount = accountService.createAccount(customerId, exceptionMessage, account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }


    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to delete account as no account was found matching the provided account ID: " + accountId;

        int code = HttpStatus.NO_CONTENT.value();
        String messages = "Successfully deleted account matching the provided account ID: " + accountId;
        accountService.deleteAccount(accountId,exceptionMessage);
        SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages);
        return new ResponseEntity<>(successResponse,HttpStatus.NO_CONTENT);
    }
}
