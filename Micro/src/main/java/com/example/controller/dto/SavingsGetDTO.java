package com.example.controller.dto;


import com.example.enums.Status;
import com.example.model.others.Money;
import com.example.model.others.User;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class SavingsGetDTO {

    private Long id;
    @NotNull(message = "A balance is required")
    private Money balance;
    private Date dateOfCreation;
    @NotNull (message = "An user is required ")
    private User user;
    private final Money penaltyFee = new Money(new BigDecimal(40));
    private List<TransactionDTO> receivedTransactions;
    private List<TransactionDTO> sentTransactions;
    @NotNull(message = "A secretKey is required")
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;
    @DecimalMax(value = "1000", message = "The minimumBalance must be less than 1000")
    @DecimalMin(value = "100", message = "The minimumBalance must be greater then 100")
    private BigDecimal minimumBalance = new BigDecimal(1000);
    private BigDecimal interestRate = new BigDecimal(0.1).setScale(2,RoundingMode.HALF_UP);;
    private Date lastInterestDate;

    public SavingsGetDTO(){

    }

    public SavingsGetDTO(Long id,
                         @NotNull(message = "A balance is required") Money balance,
                         Date dateOfCreation, User user,
                         List<TransactionDTO> receivedTransactions,
                         List<TransactionDTO> sentTransactions,
                         @NotNull(message = "A secretKey is required") String secretKey,
                         Status status,
                         @DecimalMax(value = "1000", message = "The minimumBalance must be less than 1000")
                         @DecimalMin(value = "100", message = "The minimumBalance must be greater then 100")
                         BigDecimal minimumBalance,
                         Date lastInterestDate) {
        this.id = id;
        this.balance = balance;
        this.dateOfCreation = dateOfCreation;
        this.user = user;
        this.receivedTransactions = receivedTransactions;
        this.sentTransactions = sentTransactions;
        this.secretKey = secretKey;
        this.status = status;
        this.minimumBalance = minimumBalance;
        this.lastInterestDate = lastInterestDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public List<TransactionDTO> getReceivedTransactions() {
        return receivedTransactions;
    }

    public void setReceivedTransactions(List<TransactionDTO> receivedTransactions) {
        this.receivedTransactions = receivedTransactions;
    }

    public List<TransactionDTO> getSentTransactions() {
        return sentTransactions;
    }

    public void setSentTransactions(List<TransactionDTO> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Date getLastInterestDate() {
        return lastInterestDate;
    }

    public void setLastInterestDate(Date lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
    }
}
