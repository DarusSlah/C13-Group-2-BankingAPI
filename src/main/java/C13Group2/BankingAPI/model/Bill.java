package C13Group2.BankingAPI.model;


import C13Group2.BankingAPI.enums.BillStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    @Column(name = "status")
@Enumerated(EnumType.STRING)
 private BillStatus status;
    @Column(name = "payee")
 private String payee;
    @Column(name = "nickname")
 private String nickname;
    @Column(name = "creation_date")
 private LocalDate creation_date;
    @Column(name = "recurring_date")
 private Integer recurring_date;
    @Column(name = "upcoming_payment")
 private LocalDate upcoming_payment;
    @Column(name = "payment_amount")
 private Double payment_amount;
 @ManyToOne
 @JoinColumn(name = "account_id",nullable = false)
 @OnDelete(action = OnDeleteAction.CASCADE)
 private Account account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRecurring_date() {
        return recurring_date;
    }

    public void setRecurring_date(Integer recurring_date) {
        this.recurring_date = recurring_date;
    }


    public Double getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(Double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public BillStatus getStatus() {
        return status;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public LocalDate getUpcoming_payment() {
        return upcoming_payment;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public void setUpcoming_payment(LocalDate upcoming_payment) {
        this.upcoming_payment = upcoming_payment;
    }
    public Account getAccount() {
        return account;
    }public void setAccount(Account account) {
        this.account = account;
    }

}
