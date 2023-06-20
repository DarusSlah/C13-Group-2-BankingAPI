package C13Group2.BankingAPI;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill,Long> {
    @Query("SELECT b FROM Bill b JOIN b.account a WHERE a.id = :accountId")// selects Bill entities by joining them with their associated Account entities and filters the results based on the accountId parameter. It retrieves bills for a specific account ID.
    Iterable<Bill> getAllBillsByAccountId(Long accountId);
    @Query("SELECT b FROM Bill b JOIN b.account a JOIN a.customer c WHERE c.id = :customerId")
    Iterable<Bill> getAllBillsByCustomerId(Long customerId);
}
