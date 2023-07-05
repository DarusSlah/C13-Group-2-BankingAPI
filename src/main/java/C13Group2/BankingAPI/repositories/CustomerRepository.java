package C13Group2.BankingAPI.repositories;

import C13Group2.BankingAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {
    @Query("SELECT a.customer FROM Account a WHERE a.id = :accountId")
    Customer findCustomerByAccountId(Long accountId);

}
