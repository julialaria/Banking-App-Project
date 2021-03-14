package edge.dto;



import edge.models.Money;
import edge.models.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AccountGetDTO {

    private Long id;
    @NotNull(message = "A balance is required")
    private Money balance;
    private Date dateOfCreation;
    @NotNull (message = "An user is required ")
    private User user;
    private final Money penaltyFee = new Money(new BigDecimal(40));
    private List<TransactionDTO> receivedTransactions;
    private List<TransactionDTO> sentTransactions;

    public AccountGetDTO(){

    }

    public AccountGetDTO(Long id, @NotNull(message = "A balance is required") Money balance, Date dateOfCreation, @NotNull(message = "An user is required ") User user, List<TransactionDTO> receivedTransactions, List<TransactionDTO> sentTransactions) {
        this.id = id;
        this.balance = balance;
        this.dateOfCreation = dateOfCreation;
        this.user = user;
        this.receivedTransactions = receivedTransactions;
        this.sentTransactions = sentTransactions;
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
}
