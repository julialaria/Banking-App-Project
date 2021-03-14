package com.example.controller.dto;

import com.example.enums.Status;
import com.example.model.others.Money;
import com.example.model.others.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CheckingGetDTO {

    private Long id;
    @NotNull(message = "A balance is required")
    private Money balance;
    private Date dateOfCreation;
    @NotNull (message = "An user is required")
    private User user;
    private final Money penaltyFee = new Money(new BigDecimal(40));
    private List<TransactionDTO> receivedTransactions;
    private List<TransactionDTO> sentTransactions;
    @NotNull(message = "A secretKey is required")
    private String secretKey;
    private final BigDecimal minimumBalance = new BigDecimal(250);
    private final Money monthlyMaintenanceFee = new Money(new BigDecimal(12));
    private Status status;
    private Date lastMaintenanceFee;

    public CheckingGetDTO(){

    }

    public CheckingGetDTO(Long id, @NotNull(message = "A balance is required") Money balance, Date dateOfCreation, @NotNull(message = "An user is required") User user, List<TransactionDTO> receivedTransactions, List<TransactionDTO> sentTransactions, @NotNull(message = "A secretKey is required") String secretKey, Status status, Date lastMaintenanceFee) {
        this.id = id;
        this.balance = balance;
        this.dateOfCreation = dateOfCreation;
        this.user = user;
        this.receivedTransactions = receivedTransactions;
        this.sentTransactions = sentTransactions;
        this.secretKey = secretKey;
        this.status = status;
        this.lastMaintenanceFee = lastMaintenanceFee;
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

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getLastMaintenanceFee() {
        return lastMaintenanceFee;
    }

    public void setLastMaintenanceFee(Date lastMaintenanceFee) {
        this.lastMaintenanceFee = lastMaintenanceFee;
    }
}
