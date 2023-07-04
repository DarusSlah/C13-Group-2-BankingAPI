package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.model.Customer;
import C13Group2.BankingAPI.response.SuccessResponse;
import C13Group2.BankingAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/customers")
    public ResponseEntity<?> getAllCustomers() {
        int code = HttpStatus.OK.value();
       String message = "Successfully fetched all customers";
       Iterable<Customer> data = customerService.getAllCustomers();
        SuccessResponse <Iterable<Customer>> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);

    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        int code = HttpStatus.OK.value();
        String message = "Successfully fetched customer matching the provided customer ID: " + id;
        Customer data = customerService.getCustomerById(id);
       SuccessResponse<Customer> successResponse = new SuccessResponse<>(code,message,data);
       return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
        int code = HttpStatus.CREATED.value();
        String message = "Successfully created new customer";
        Customer data = customerService.createCustomer(customer);
        SuccessResponse<Customer> successResponse = new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        int code = HttpStatus.OK.value();
        String message = "Successfully updated customer matching the provided customer ID: " + id;
        Customer data = customerService.updateCustomer(id, customer);

        SuccessResponse<Customer>successResponse= new SuccessResponse<>(code,message,data);
        return new ResponseEntity<>(successResponse,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
