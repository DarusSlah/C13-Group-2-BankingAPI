package C13Group2.BankingAPI.repositories;

import C13Group2.BankingAPI.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
