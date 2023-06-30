package C13Group2.BankingAPI.model;

<<<<<<< Updated upstream

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private AccountType accountType; //TODO: Add an AccountType Class
=======
import C13Group2.BankingAPI.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @JsonProperty("type")
    @NotNull
    private AccountType accountType;
    @JsonProperty("nickname")
    @Column(name = "nickname")
>>>>>>> Stashed changes
    private String nickname;
    @JsonProperty("rewards")
    @Column(name = "rewards")
    private Integer rewards;
    @JsonProperty("balance")
    @Column(name = "balance")
    private Double balance;

<<<<<<< Updated upstream
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "customerId",nullable = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
  private Customer customer; // TODO: Customer Class needs to be added to not bark
=======
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;
>>>>>>> Stashed changes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRewards() {
        return rewards;
    }

    public void setRewards(Integer rewards) {
        this.rewards = rewards;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

<<<<<<< Updated upstream
=======
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
>>>>>>> Stashed changes
    public Customer getCustomer() {
        return customer;
    }

<<<<<<< Updated upstream
    public void setCustomer(Customer customer) { // TODO: make sure Customer is added to work
=======
    public void setCustomer(Customer customer) {
>>>>>>> Stashed changes
        this.customer = customer;
    }
}
