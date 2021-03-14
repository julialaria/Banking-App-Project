package com.example.model.Account;

import com.example.model.others.Money;
import com.example.model.others.Transaction;
import com.example.model.others.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    private Money balance;
    private Date dateOfCreation;
    @ManyToOne
    @JoinColumn(name = "primary_owner")
    private User user;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "penalty_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "penalty_currency"))
    })
    private final Money penaltyFee = new Money(new BigDecimal(40));
    @OneToMany(mappedBy = "destinationAccount")
    @JsonIgnore
    private List<Transaction> receivedTransactions;
    @OneToMany(mappedBy = "origenAccount")
    @JsonIgnore
    private List<Transaction> sentTransactions;

    public Account(){
        this.dateOfCreation=new Date();
    }

    public Account(Money balance, User user) {
        this.balance = balance;
        this.dateOfCreation = new Date();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
            this.balance = balance;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public List<Transaction> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<Transaction> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }
}
