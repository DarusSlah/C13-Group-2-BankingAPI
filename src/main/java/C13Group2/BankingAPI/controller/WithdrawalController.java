package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.model.Withdrawal;
import C13Group2.BankingAPI.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<Iterable<Withdrawal>> getAllWithdrawals(){
        return new ResponseEntity<>(withdrawalService.getAllWithdrawals(), HttpStatus.OK);
    }

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Withdrawal> getWithdrawalById(@PathVariable Long id){
        return new ResponseEntity<>(withdrawalService.getWithdrawalById(id), HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<Withdrawal> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal){
        Withdrawal createdWithdrawal = withdrawalService.createWithdrawal(accountId, withdrawal.getMedium(), withdrawal.getAmount(), withdrawal.getDescription());
        return new ResponseEntity<>(createdWithdrawal, HttpStatus.CREATED);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<Withdrawal> updateWithdrawal(@PathVariable Long id, @RequestBody Withdrawal withdrawal){
        Withdrawal updatedWithdrawal = withdrawalService.updateWithdrawal(id, withdrawal.getMedium(), withdrawal.getAmount(), withdrawal.getDescription());
        return new ResponseEntity<>(updatedWithdrawal, HttpStatus.OK);
    }

    @DeleteMapping("/withdrawals/{withdrawalId} ")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable Long id){
        withdrawalService.deleteWithdrawal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
