package C13Group2.BankingAPI.service;

import C13Group2.BankingAPI.dto.CreateWithdrawalDTO;
import C13Group2.BankingAPI.dto.UpdateWithdrawalDTO;
import C13Group2.BankingAPI.enums.Medium;
import C13Group2.BankingAPI.enums.TransactionType;
import C13Group2.BankingAPI.enums.WithdrawalStatus;
import C13Group2.BankingAPI.exceptions.ResourceNotFoundException;
import C13Group2.BankingAPI.model.Account;
import C13Group2.BankingAPI.model.Deposit;
import C13Group2.BankingAPI.model.Withdrawal;
import C13Group2.BankingAPI.repositories.AccountRepository;
import C13Group2.BankingAPI.repositories.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawalService {
    @Autowired
    WithdrawalRepository withdrawalRepository;

    @Autowired
    AccountRepository accountRepository;

    private void verifyIfAccountExists(Long accountId, String exceptionMessage) throws ResourceNotFoundException {
        if (!(accountRepository.existsById(accountId))) {
            throw new ResourceNotFoundException(exceptionMessage);
        }
    }

    public Iterable<Withdrawal> getAllWithdrawals() {
        List<Withdrawal> withdrawalList = new ArrayList<>();
        for (Withdrawal withdrawal : withdrawalRepository.findAll()) {
            if (withdrawal.getType() == TransactionType.WITHDRAWAL) {
                withdrawalList.add(withdrawal);
            }
        }
        return withdrawalList;
    }

    public Withdrawal getWithdrawalById(Long withdrawalId) {
        return withdrawalRepository.findById(withdrawalId).orElse(null);

    }

    public Withdrawal createWithdrawal(Long accountId, String exceptionMessage, CreateWithdrawalDTO createWithdrawalDTO) {
        verifyIfAccountExists(accountId, exceptionMessage);
        LocalDate currentDate = LocalDate.now();
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setType(TransactionType.WITHDRAWAL);
        withdrawal.setStatus(WithdrawalStatus.COMPLETED);
        withdrawal.setTransaction_Date(currentDate);
        withdrawal.setMedium(createWithdrawalDTO.getMedium());
        withdrawal.setAmount(createWithdrawalDTO.getAmount());
        withdrawal.setDescription(createWithdrawalDTO.getDescription());

        // taking out money from the account
        withdrawal.setAccount(accountRepository.findById(accountId).orElse(null));
        Account withdrawalFunds = accountRepository.findById(accountId).orElse(null);
        withdrawalFunds.setBalance(withdrawalFunds.getBalance() - withdrawal.getAmount());
        accountRepository.save(withdrawalFunds);


        return withdrawalRepository.save(withdrawal);
    }

    public Withdrawal updateWithdrawal(Long withdrawalId, UpdateWithdrawalDTO updateWithdrawalDTO) {
        Withdrawal existingWithdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        existingWithdrawal.setDescription(updateWithdrawalDTO.getDescription());

        return withdrawalRepository.save(existingWithdrawal);
    }

    public Withdrawal deleteWithdrawal(Long withdrawalId) {
        Withdrawal cancelWithdrawal = withdrawalRepository.findById(withdrawalId).orElse(null);
        if (cancelWithdrawal != null) {
            withdrawalRepository.deleteById(withdrawalId);
            return cancelWithdrawal;
        } else {
            throw new EntityNotFoundException("Withdrawal with id " + withdrawalId + " not found");
        }
    }


}



