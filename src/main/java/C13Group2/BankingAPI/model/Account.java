package C13Group2.BankingAPI.model;


import C13Group2.BankingAPI.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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

    private String nickname;
    @JsonProperty("rewards")
    @Column(name = "rewards")
    private Integer rewards;
    @JsonProperty("balance")
    @Column(name = "balance")
    private Double balance;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
   @JoinColumn(name = "customerId",nullable = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
  private Customer customer; // TODO: Customer Class needs to be added to not bark

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


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) { // TODO: make sure Customer is added to work

            this.customer = customer;
        }
    }
