package C13Group2.BankingAPI.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private AccountType accountType; //TODO: Add an AccountType Class
    private String nickname;
    private Integer rewards;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) { // TODO: make sure Customer is added to work
        this.customer = customer;
    }
}
