package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.model.Address;
import C13Group2.BankingAPI.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
    @Autowired
    private AddressRepository addressRepository;

    //add address
    public void addAddress(Address address){
        logger.info("Creating new address: ", address);
        addressRepository.save(address);
    }

    //update address
    public void updateAddressById(Long id, Address address){
        logger.info("Updating address with ID: , New address: ", id, address);
        address.setId(id);
        addressRepository.save(address);
    }
    //delete address
    public void deleteAddressById(Long id){
        logger.info("Deleting address with ID: ", id);
        addressRepository.deleteById(id);
    }

    //learn how to explain this code below
    //get all addresses
    public Iterable<Address> getAllAddresses() {
        logger.info("Retrieving all addresses");
        return addressRepository.findAll();
    }

    //learn how to explain this code below
    //get address by id
    public Optional<Address> getAddressById(Long id){
        logger.info("Retrieving address by id of: ", id);
        addressRepository.findById(id);
        return null;
    }


    
}
