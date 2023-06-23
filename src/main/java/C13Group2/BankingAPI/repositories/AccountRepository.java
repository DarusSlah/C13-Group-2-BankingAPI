package C13Group2.BankingAPI.repositories;

import C13Group2.BankingAPI.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
