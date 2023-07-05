package C13Group2.BankingAPI.model;


import C13Group2.BankingAPI.enums.DepositStatus;
import C13Group2.BankingAPI.enums.Medium;
import C13Group2.BankingAPI.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @JsonProperty("type")
    @NotNull
    private TransactionType type;
    @Column(name = "transaction_date")
    @JsonProperty("transaction_date")
    private LocalDate transaction_date;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @JsonProperty("status")
    private DepositStatus status;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "medium")
    @JsonProperty("medium")
    private Medium medium;
    @Column(name = "amount")
    @JsonProperty("amount")
    private Double amount;
    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDate getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(LocalDate transaction_date) {
        this.transaction_date = transaction_date;
    }

    public DepositStatus getStatus() {
        return status;
    }

    public void setStatus(DepositStatus status) {
        this.status = status;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


