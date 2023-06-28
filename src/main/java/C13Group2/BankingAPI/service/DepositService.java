package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.model.Deposit;
import C13Group2.BankingAPI.repositories.DepositRepository;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    DepositRepository depositRepository;

    public Iterable<Deposit> getAllDepositByAccountId(){
        return depositRepository.findAll();
    }
    public Deposit getDepositById(Long depositId){
        return depositRepository.findById(depositId).orElse(null);
    }
    public Deposit createDeposit(Deposit deposit){
        return depositRepository.save(deposit);
    }
    public Deposit updateDeposit(Deposit deposit){
        return depositRepository.save(deposit);
    }
    public void deleteDeposit(Long depositId){
        depositRepository.deleteById(depositId);
    }
}
