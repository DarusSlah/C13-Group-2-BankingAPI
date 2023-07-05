package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.service.BillServices;
import C13Group2.BankingAPI.response .SuccessResponse;
import C13Group2.BankingAPI.model.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BillController {

    @Autowired
    private BillServices billService;

    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsByAccountId(@PathVariable Long accountId) {
        String exceptionMessage = "Unable to fetch bills as no account was found matching the provided account ID: " + accountId;
        int code = HttpStatus.OK.value();
        String messages = " Successfully retrieved Bills associated with Account Id";
        Iterable<Bill> data = billService.getBillsByAccountId(accountId,exceptionMessage);
        SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages,data);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/bills")
    public ResponseEntity<?> getAllBillsByCustomerId(@PathVariable Long customerId) {
        String exceptionMessage = "Unable to fetch bills as no customer was found matching the provided customer ID: " + customerId;
        int code = HttpStatus.OK.value();
        String messages = "Successfully retrieved Bills associated with Customer Id:" + customerId;
        Iterable<Bill> data = billService.getBillsByCustomerId(customerId,exceptionMessage);
        SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages,data);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillWithId(@PathVariable Long billId) {
        int code = HttpStatus.OK.value();
        String exceptionMessage ="error fetching bill with id:" + billId;
        String messages = "Successfully retrieved Bills associated with the  Id of " + billId;
        Bill data = billService.getBillById(billId,exceptionMessage);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(code,messages,data);

        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }


    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?>createBill(@PathVariable Long accountId, @Valid @RequestBody Bill bill) {
        int code = HttpStatus.CREATED.value();
        String exceptionMessage = "Error creating bill: Account with ID of: " + accountId + " not found";
        String messages = "Bill has been successfully created and was added it to the account";
        Bill data = billService.createBill(accountId, bill,exceptionMessage);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(code,messages,data);

        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }


    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable Long billId, @Valid @RequestBody Bill bill) {
        int code = HttpStatus.ACCEPTED.value();
        String exceptionMessage = "Bill ID of:" + billId + " does not exist";
        String messages = "Accepted bill modification";
        billService.updateBill(billId,bill,exceptionMessage);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(code,messages);

        return (new ResponseEntity<>(successResponse, HttpStatus.ACCEPTED));
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        int code = HttpStatus.NO_CONTENT.value();
        String exceptionMessage = "Id of :" + billId + " does not exist in bills";
        String messages = "Bill with an Id of " + billId + " has been successfully deleted";
        billService.deleteBill(billId,exceptionMessage );
        SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages);

        return (new ResponseEntity<>(successResponse, HttpStatus.NO_CONTENT));
    }
}
