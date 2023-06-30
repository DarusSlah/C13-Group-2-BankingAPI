package C13Group2.BankingAPI.repositories;

import C13Group2.BankingAPI.model.Deposit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit, Long> {

    @Query("SELECT t FROM Deposit t WHERE t.account.id = :accountId")
    Iterable<Deposit> findAllDepositByAccountId(Long accountId);
}
