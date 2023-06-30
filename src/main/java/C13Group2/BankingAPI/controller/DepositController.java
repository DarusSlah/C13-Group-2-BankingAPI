package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.model.Deposit;
import C13Group2.BankingAPI.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class DepositController {
    DepositService depositService;
    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<Iterable<Deposit>> getAllDepositsByAccountId() {
        return new ResponseEntity<>(depositService.getAllDepositByAccountId(), HttpStatus.OK);
    }
    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<Deposit> getDepositById(@PathVariable Long depositId) {
        return new ResponseEntity<>(depositService.getDepositById(depositId), HttpStatus.OK);
    }
    @PostMapping("/accounts/{accountId}")
    public ResponseEntity<Deposit> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit) {
        Deposit newDeposit = depositService.createDeposit(accountId, deposit.getMedium(), deposit.getAmount(), deposit.getDescription());
        return new ResponseEntity<>(newDeposit, HttpStatus.CREATED);
    }
    @PutMapping("/{depositId}")
    public ResponseEntity<Deposit> updateDeposit(@PathVariable Long depositId,@RequestBody Deposit deposit) {
        return new ResponseEntity<>(depositService.getDepositById(depositId), HttpStatus.OK);
    }
    @DeleteMapping("/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId) {
        depositService.deleteDeposit(depositId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
