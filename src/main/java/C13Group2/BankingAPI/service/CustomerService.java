package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.exceptions.ResourceNotFoundException;
import C13Group2.BankingAPI.model.Address;
import C13Group2.BankingAPI.model.Customer;


import C13Group2.BankingAPI.repositories.AccountRepository;
import C13Group2.BankingAPI.repositories.AddressRepository;
import C13Group2.BankingAPI.repositories.CustomerRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private  AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private void verifyIfAccountExists(Long accountId, String exceptionMessage)throws ResourceNotFoundException {
        if(!(accountRepository.existsById(accountId))){
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }
    private void verifyIfCustomerExists(Long customerId, String exceptionMessage) throws ResourceNotFoundException{
        if(!(customerRepository.existsById(customerId))){
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }
    public/*List*/ Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id, String exceptionMessage ) {
        verifyIfCustomerExists(id, exceptionMessage);
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {

        for(Address address: customer.getAddresses()){
            address.setCustomer(customer);
        }
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer, String exceptionMessage) {
        verifyIfCustomerExists(id, exceptionMessage);
            Customer existingCustomer = customerRepository.findById(id).orElse(null);
            if (existingCustomer != null) {
                if (updatedCustomer.getFirstName() != null && !updatedCustomer.getFirstName().isBlank()) {
                    existingCustomer.setFirstName(updatedCustomer.getFirstName().trim());
                }
                if (updatedCustomer.getLastName() != null && !updatedCustomer.getLastName().isBlank()) {
                    existingCustomer.setLastName(updatedCustomer.getLastName().trim());
                }
                if (updatedCustomer.getAddresses() != null && !updatedCustomer.getAddresses().isEmpty()) {
                    Set<Address> oldAddresses = new HashSet<>(existingCustomer.getAddresses());
                    existingCustomer.getAddresses().clear();
                    addressRepository.deleteAll(oldAddresses);
                    for (Address address : updatedCustomer.getAddresses()) {
                        address.setCustomer(existingCustomer);
                        existingCustomer.getAddresses().add(address);
                    }
                }
                return customerRepository.save(existingCustomer);
            }
            return null;
        }


        public void deleteCustomer(Long id, String exceptionMessage) {
        verifyIfCustomerExists(id, exceptionMessage);
        customerRepository.deleteById(id);
    }
    public Customer getCustomerByAccountId(Long accountId, String exceptionMessage) {
        verifyIfAccountExists(accountId, exceptionMessage);
        return this.customerRepository.findCustomerByAccountId(accountId);
    }

}

