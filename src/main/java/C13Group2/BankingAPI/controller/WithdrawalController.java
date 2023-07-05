package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.dto.CreateWithdrawalDTO;
import C13Group2.BankingAPI.dto.UpdateWithdrawalDTO;
import C13Group2.BankingAPI.model.Withdrawal;
import C13Group2.BankingAPI.response.SuccessResponse;
import C13Group2.BankingAPI.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WithdrawalController {
    @Autowired
    private WithdrawalService withdrawalService;

    @GetMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> getAllWithdrawals(){
        int code = HttpStatus.OK.value();
        String message = "Successfully fetched all withdrawals";
        Iterable<Withdrawal> data = withdrawalService.getAllWithdrawals();
        SuccessResponse<?> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @GetMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> getWithdrawalById(@PathVariable Long withdrawalId){
        String exceptionMessage ="Unable to fetch withdrawal";
        int code = HttpStatus.OK.value();
        String message = "Successfully fetched withdrawal matching the provided transaction ID: " + withdrawalId;
        Withdrawal data = withdrawalService.getWithdrawalById(withdrawalId,exceptionMessage);
        SuccessResponse<Withdrawal> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping("/accounts/{accountId}/withdrawals")
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @Valid @RequestBody CreateWithdrawalDTO createWithdrawalDTO){
        String exceptionMessage = "not able to create a new withdrawal";

        int code =HttpStatus.CREATED.value();
        String message="Successfully created new withdrawal for account with ID: " + accountId;
        Withdrawal data = withdrawalService.createWithdrawal(accountId,exceptionMessage,createWithdrawalDTO);
        SuccessResponse<Withdrawal> successResponse = new SuccessResponse<>(code,message,data);

        return new ResponseEntity<>( successResponse,HttpStatus.CREATED);
    }

    @PutMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<?> updateWithdrawal(@PathVariable Long withdrawalId, @Valid @RequestBody UpdateWithdrawalDTO updateWithdrawalDTO){
        int code = HttpStatus.OK.value();
        String errorMessage ="Unable to update withdrawal due to invalid id";
        String message = "Successfully updated withdrawal matching the provided transaction ID: " + withdrawalId;
        Withdrawal data = withdrawalService.updateWithdrawal(withdrawalId,updateWithdrawalDTO,errorMessage);
        SuccessResponse<Withdrawal> successResponse = new SuccessResponse<>(code, message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }


    @DeleteMapping("/withdrawals/{withdrawalId}")
    public ResponseEntity<SuccessResponse<?>> deleteWithdrawal(@PathVariable Long withdrawalId){
        withdrawalService.deleteWithdrawal(withdrawalId);
        int code = HttpStatus.OK.value();
        String message = "Successfully cancelled withdrawal matching the provided transaction ID: " + withdrawalId;
        SuccessResponse<?> successResponse = new SuccessResponse<>(code, message, null);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }


}
