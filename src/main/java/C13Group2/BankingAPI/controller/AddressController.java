package C13Group2.BankingAPI.controller;

import C13Group2.BankingAPI.model.Address;
import C13Group2.BankingAPI.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AddressController {
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private final AddressService addressService;
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/addresses")
    public ResponseEntity<Void> createAddress(@RequestBody Address address) {
        logger.info("Creating new address: ", address);
        addressService.addAddress(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addresses/{id}")
    public ResponseEntity<Void> updateAddressById(@PathVariable Long id, @RequestBody Address address){
        logger.info("Updating address with ID: , New address: ", id, address);
        addressService.updateAddressById(id, address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<Void> deleteAddressById(@PathVariable Long id){
        logger.info("Deleting address with ID: ", id);
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/addresses")
    public ResponseEntity<Iterable<Address>> getAllAddresses() {
        logger.info("Retrieving all addresses");
        Iterable<Address> addresses = addressService.getAllAddresses();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/addresses/{id}")
    public ResponseEntity<Optional<Address>> getAddressById(@PathVariable Long id) {
        logger.debug("Retrieving address with ID: ", id);
        Optional<Address> address = addressService.getAddressById(id);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }




}
