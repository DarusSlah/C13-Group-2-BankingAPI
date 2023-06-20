package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.exceptions.ResourceNotFoundException;
import C13Group2.BankingAPI.model.Bill;
import C13Group2.BankingAPI.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BillServices {

//    private AccountRepository accountRepository;
    @Autowired
    private BillRepository billRepository;

//    private CustomerRepository customerRepository;

//    private void verifyIfAccountExists(Long accountId)throws ResourceNotFoundException{
//        if(!(accountRepository.existsById(accountId))){
//            throw new ResourceNotFoundException("“error fetching bills");
//        }
//    }

    private void verifyIfBillExists(Long billId) throws ResourceNotFoundException {
        if(!(billRepository.existsById(billId))){
            throw new ResourceNotFoundException( "“error fetching bill with id:" + billId);
        }
    }

//    private void verifyIfCustomerExists(Long customerId) throws ResourceNotFoundException{
//        if(!(billRepository.existsById(customerId))){
//            throw new ResourceNotFoundException( "“error fetching bills");
//        }
//    }


//    public Bill createBill(Long accountId,Bill bills){
//        verifyIfAccountExists(accountId);
//      accountRepository.findById(accountId).orElseThrow(()-> new ResourceNotFoundException("Error creating bill:" +" Account with ID of:"+ accountId + " not found"));
//        Bill newBill = new Bill();
//        newBill.setStatus(bills.getStatus());
//        newBill.setPayee(bills.getPayee());
//        newBill.setNickname(bills.getNickname());
//        newBill.setRecurring_date(bills.getRecurring_date());
//        LocalDate date = LocalDate.now();
//        LocalDate nextPaymentDate = LocalDate.of(date.getYear(),date.getMonth(),bills.getRecurring_date());
//        if(nextPaymentDate.isBefore(date)){
//            nextPaymentDate = nextPaymentDate.plusMonths(1);
//        }
//        newBill.setUpcoming_payment(nextPaymentDate);
//        newBill.setCreation_date(date);
//        newBill.setPayment_amount(bills.getPayment_amount());
//        newBill.setAccount(accountRepository.findById(accountId).orElse(null));
//        return billRepository.save(bills);
//
//
//
//    }


    public Bill getBillById(Long id){
        verifyIfBillExists(id);
        return billRepository.findById(id).orElse(null);
    }

    public Bill updateBill(Long billId,Bill bill){
        verifyIfBillExists(billId);
        Bill updatebill = billRepository.findById(billId).orElseThrow(()-> new ResourceNotFoundException("Bill ID does not exist"));
        if(updatebill != null) {
            updatebill.setPayment_amount(bill.getPayment_amount());
            updatebill.setStatus(bill.getStatus());
            updatebill.setPayee(bill.getPayee());
            updatebill.setNickname(bill.getNickname());
            updatebill.setRecurring_date(bill.getRecurring_date());
             LocalDate date = LocalDate.now();
             LocalDate nextPaymentDate = LocalDate.of(date.getYear(),date.getMonth(),bill.getRecurring_date());
             if (nextPaymentDate.isBefore(date)){
                 nextPaymentDate = nextPaymentDate.plusMonths(1);
             }
             updatebill.setUpcoming_payment(nextPaymentDate);
        }

     return billRepository.save(bill);
    }


    public void deleteBill(Long billId)throws ResourceNotFoundException {
        verifyIfBillExists(billId);
       try {
           billRepository.deleteById(billId);
       } catch (Exception e){
           throw new ResourceNotFoundException(billId + " doesn't exist in bills");
       }

    }



//    public Iterable<Bill> getBillsByAccountId(Long accountId){
//        verifyIfAccountExists(accountId);
//        return billRepository.getAllBillsByAccountId(accountId);
//
//
//    }

//public Iterable<Bill>getBillsByCustomerId(Long customerId){
//        verifyIfCustomerExists(customerId);
//        return billRepository.getAllBillsByCustomerId(customerId);
//}

}
