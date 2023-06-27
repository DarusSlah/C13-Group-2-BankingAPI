package C13Group2.BankingAPI.repositories;

import C13Group2.BankingAPI.model.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit, Long> {
}
