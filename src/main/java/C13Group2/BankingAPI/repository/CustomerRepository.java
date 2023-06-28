package C13Group2.BankingAPI.repository;

import C13Group2.BankingAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {

}
