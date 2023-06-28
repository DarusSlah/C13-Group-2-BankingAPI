package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.service.BillServices;
import C13Group2.BankingAPI.response.SuccessResponse;
import C13Group2.BankingAPI.model.Bill;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {


    @Autowired
    private BillServices billService;


    @GetMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?> getAllBillsByAccountId(@PathVariable Long accountId) {
     int code = HttpStatus.OK.value();
     String messages = " Successfully retrieved Bills associated with Account Id";
     Iterable<Bill> data = billService.getBillsByAccountId(accountId);
     SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages,data);
     return new ResponseEntity<>(successResponse,HttpStatus.OK);

    }

//    @GetMapping("/customers/{customerId}/bills")
//    public ResponseEntity<?> getAllBillsByCustomerId(@PathVariable Long customerId) {
//        int code = HttpStatus.OK.value();
//        String messages = "Successfully retrieved Bills associated with Customer Id";
//        Iterable<Bill> data = billService.getBillsByCustomerId(customerId);
//        SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages,data);
//
//        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
//    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<?> getBillWithId(@PathVariable Long billId) {
        int code = HttpStatus.OK.value();
        String messages = "Successfully retrieved Bills associated with the  Id of" + billId;
        Bill data = billService.getBillById(billId);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(code,messages,data);


        return (new ResponseEntity<>(successResponse, HttpStatus.OK));
    }

    @PostMapping("/accounts/{accountId}/bills")
    public ResponseEntity<?>createBill(@PathVariable Long accountId, @Valid @RequestBody Bill bill) {
        int code = HttpStatus.CREATED.value();
        String messages = "Bill has been successfully created and was added it to the account";
        Bill data = billService.createBill(accountId, bill);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(code,messages,data);

        return (new ResponseEntity<>(successResponse, HttpStatus.CREATED));
    }

    @PutMapping("/bills/{billId}")
    public ResponseEntity<?> updateBill(@PathVariable Long billId, @Valid @RequestBody Bill bill) {

        int code = HttpStatus.ACCEPTED.value();
        String messages = "Accepted bill modification";
        billService.updateBill(billId,bill);
        SuccessResponse<Bill> successResponse = new SuccessResponse<>(code,messages);

        return (new ResponseEntity<>(successResponse, HttpStatus.ACCEPTED));
    }

    @DeleteMapping("/bills/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable Long billId) {
        int code = HttpStatus.NO_CONTENT.value();
        String messages = "Bill with an Id of" + billId + " has been successfully deleted";
        billService.deleteBill(billId);
        SuccessResponse<?> successResponse = new SuccessResponse<>(code,messages);

        return (new ResponseEntity<>(successResponse, HttpStatus.NO_CONTENT));
    }

}
