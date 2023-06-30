package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.model.Deposit;
import C13Group2.BankingAPI.response.SuccessResponse;
import C13Group2.BankingAPI.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class DepositController {
    DepositService depositService;


    @GetMapping("/deposits")
    public ResponseEntity<?> getAllDeposits() {
        int code = HttpStatus.OK.value();
        String message = "Successfully fetched all deposits";
        Iterable<Deposit> data = depositService.getAllDeposits();
        SuccessResponse<Iterable<Deposit>> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> getAllDepositsByAccountId(@PathVariable Long accountId) {
        int code = HttpStatus.OK.value();
        String message = "Successfully fetched deposits matching the provided account ID: " + accountId;
        Iterable<Deposit> data = depositService.getAllDepositByAccountId(accountId);
        SuccessResponse<Iterable<Deposit>> successResponse = new SuccessResponse<>(code,message,data);

        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
    @GetMapping("/deposits/{depositId}")
    public ResponseEntity<?> getDepositById(@PathVariable Long depositId) {
        int code = HttpStatus.OK.value();
        String message = "Successfully fetched deposit matching the provided transaction ID: " + depositId;
        Deposit data = depositService.getDepositById(depositId);
        SuccessResponse<Deposit> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    @PostMapping("/accounts/{accountId}")
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit) {
        int code = HttpStatus.CREATED.value();
        String message = "Successfully created new deposit for account with ID: " + accountId;
        Deposit data = depositService.createDeposit(accountId, deposit.getMedium(), deposit.getAmount(), deposit.getDescription());
        SuccessResponse <Deposit> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{depositId}")
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositId,@RequestBody Deposit deposit) {
        int code = HttpStatus.CREATED.value();
        String message = "Successfully updated deposit matching the provided transaction ID: " + depositId;
        Deposit data = depositService.updateDeposit(deposit);
        SuccessResponse <Deposit> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId) {
        int code = HttpStatus.OK.value();
        String message = "Successfully cancelled deposit matching the provided transaction ID: " + depositId;
        SuccessResponse <?> successResponse = new SuccessResponse<>(code,message,null);

    return new ResponseEntity<>(successResponse,HttpStatus.NO_CONTENT);
    }
}
