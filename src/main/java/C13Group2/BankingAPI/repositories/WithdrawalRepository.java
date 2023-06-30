package C13Group2.BankingAPI.repositories;

import C13Group2.BankingAPI.model.Withdrawal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepository extends CrudRepository<Withdrawal,Long> {

}
