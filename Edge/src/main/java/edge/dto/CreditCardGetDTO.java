package edge.dto;


import edge.models.Money;
import edge.models.User;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class CreditCardGetDTO {

    private Long id;
    @NotNull(message = "A balance is required")
    private Money balance;
    private Date dateOfCreation;
    @NotNull(message = "An user is required ")
    private User user;
    private final Money penaltyFee = new Money(new BigDecimal(40));
    private List<TransactionDTO> receivedTransactions;
    private List<TransactionDTO> sentTransactions;
    @DecimalMax(value = "100000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit = new BigDecimal((100));
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "0.2")
    private BigDecimal interestRate = new BigDecimal((0.2)).setScale(2, RoundingMode.HALF_UP);
    private Date lastInterestDate;

    public CreditCardGetDTO() {

    }

    public CreditCardGetDTO(Long id, @NotNull(message = "A balance is required") Money balance, Date dateOfCreation, @NotNull(message = "An user is required ") User user, List<TransactionDTO> receivedTransactions, List<TransactionDTO> sentTransactions, @DecimalMax(value = "100000") @DecimalMin(value = "100") BigDecimal creditLimit, @DecimalMin(value = "0.1") @DecimalMax(value = "0.2") BigDecimal interestRate, Date lastInterestDate) {
        this.id = id;
        this.balance = balance;
        this.dateOfCreation = dateOfCreation;
        this.user = user;
        this.receivedTransactions = receivedTransactions;
        this.sentTransactions = sentTransactions;
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
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

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
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
