package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.exceptions.ResourceNotFoundException;
import C13Group2.BankingAPI.model.Account;
import C13Group2.BankingAPI.model.Bill;
import C13Group2.BankingAPI.repositories.AccountRepository;
import C13Group2.BankingAPI.repositories.BillRepository;
import C13Group2.BankingAPI.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BillServices {
     @Autowired
    private AccountRepository accountRepository;
     @Autowired
    private BillRepository billRepository;
    @Autowired
   private CustomerRepository customerRepository;

    private void verifyIfAccountExists(Long accountId, String exceptionMessage)throws ResourceNotFoundException{
        if(!(accountRepository.existsById(accountId))){
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }

    private void verifyIfBillExists(Long billId,String exceptionMessage) throws ResourceNotFoundException {
        if(!(billRepository.existsById(billId))){
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }

    private void verifyIfCustomerExists(Long customerId, String exceptionMessage) throws ResourceNotFoundException{
        if(!(customerRepository.existsById(customerId))){
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }


    public Bill createBill(Long accountId,Bill bill, String exceptionMessage){
        verifyIfAccountExists(accountId,exceptionMessage);
        Bill newBill = new Bill();
        newBill.setStatus(bill.getStatus());
        newBill.setPayee(bill.getPayee());
        newBill.setNickname(bill.getNickname());
        newBill.setRecurring_date(bill.getRecurring_date());
        LocalDate date = LocalDate.now();
        LocalDate nextPaymentDate = LocalDate.of(date.getYear(),date.getMonth(),bill.getRecurring_date());
        if(nextPaymentDate.isBefore(date)){
            nextPaymentDate = nextPaymentDate.plusMonths(1);
        }
        newBill.setUpcoming_payment(nextPaymentDate);
        newBill.setCreation_date(date);
        newBill.setPayment_amount(bill.getPayment_amount());
        newBill.setAccount(accountRepository.findById(accountId).orElse(null));
        return billRepository.save(newBill);
}


    public Bill getBillById(Long id, String exceptionMessage){
        verifyIfBillExists(id, exceptionMessage);
        return billRepository.findById(id).orElse(null);
    }

    public Bill updateBill(Long billId,Bill bill, String exceptionMessage
    ){ verifyIfBillExists(billId, exceptionMessage);
        Bill updatebill = billRepository.findById(billId).orElse(null);
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

     return billRepository.save(updatebill);
    }


    public void deleteBill(Long billId, String exceptionMessage)throws ResourceNotFoundException {
        verifyIfBillExists(billId, exceptionMessage);
         billRepository.deleteById(billId);

    }



    public Iterable<Bill> getBillsByAccountId(Long accountId, String exceptionMessage){
        verifyIfAccountExists(accountId, exceptionMessage);
        return billRepository.getAllBillsByAccountId(accountId);


    }

 public Iterable<Bill>getBillsByCustomerId(Long customerId, String exceptionMessage){
        verifyIfCustomerExists(customerId,exceptionMessage);
        return billRepository.getAllBillsByCustomerId(customerId);
}

}
