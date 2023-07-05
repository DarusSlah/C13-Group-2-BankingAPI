package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.dto.CreateDepositDTO;
import C13Group2.BankingAPI.dto.UpdateDepositDTO;
import C13Group2.BankingAPI.model.Deposit;
import C13Group2.BankingAPI.response.SuccessResponse;
import C13Group2.BankingAPI.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DepositController {

    @Autowired
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
    @PostMapping("/accounts/{accountId}/deposits")
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @Valid @RequestBody CreateDepositDTO createDepositDTO) {
        int code = HttpStatus.CREATED.value();
        String message = "Successfully created new deposit for account with ID: " + accountId;
        Deposit data = depositService.createDeposit(accountId, createDepositDTO);
        SuccessResponse <Deposit> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }
    @PutMapping("/deposits/{depositId}")
    public ResponseEntity<?> updateDeposit(@PathVariable Long depositId, @Valid @RequestBody UpdateDepositDTO updateDepositDTO) {
        String exceptionMessage = "Unable to update deposit";
        int code = HttpStatus.CREATED.value();
        String message = "Successfully updated deposit matching the provided transaction ID: " + depositId;
        Deposit data = depositService.updateDeposit(depositId, exceptionMessage ,updateDepositDTO);
        SuccessResponse <Deposit> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long depositId) {
        int code = HttpStatus.OK.value();
        String message = "Successfully cancelled deposit matching the provided transaction ID: " + depositId;
        Deposit data = depositService.deleteDeposit(depositId);
        SuccessResponse <?> successResponse = new SuccessResponse<>(code,message,null);

    return new ResponseEntity<>(successResponse,HttpStatus.NO_CONTENT);
    }
}
