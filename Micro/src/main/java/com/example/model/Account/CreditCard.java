package com.example.model.Account;


import com.example.model.others.Money;
import com.example.model.others.User;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DynamicUpdate
public class CreditCard extends Account {

    @DecimalMax(value = "100000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit = new BigDecimal((100));
    @DecimalMin(value = "0.1")
    @DecimalMax(value = "0.2")
    private BigDecimal interestRate = new BigDecimal((0.2)).setScale(2,RoundingMode.HALF_UP);
    private Date lastInterestDate;

    public CreditCard(){
        this.lastInterestDate = new Date();

    }

    public CreditCard(@NotNull(message = "A balance is required") Money balance,
                      @NotNull(message = "A primaryOwner is required") User user) {
        super(balance, user);
        this.lastInterestDate = new Date();
    }

    public CreditCard(@NotNull(message = "A balance is required") Money balance,
                      @NotNull(message = "A primaryOwner is required") User user,
                      Date dateOfCreation,
                      @DecimalMax(value = "100000") @DecimalMin(value = "100") BigDecimal creditLimit) {
        super(balance, user);
        this.creditLimit = creditLimit;
        this.lastInterestDate = new Date();
    }


    public CreditCard(@NotNull(message = "A balance is required")Money balance,
                      @NotNull(message = "A primaryOwner is required")User user,
                      @DecimalMin(value = "0.1") @DecimalMax(value = "0.2") BigDecimal interestRate) {
        super(balance, user);
        this.interestRate = interestRate;
        this.lastInterestDate = new Date();
    }


    public CreditCard(@NotNull(message = "A balance is required")Money balance,
                      @NotNull(message = "A primaryOwner is required")User user,
                      @DecimalMax(value = "100000") @DecimalMin(value = "100") BigDecimal creditLimit,
                      @DecimalMin(value = "0.1") @DecimalMax(value = "0.2", inclusive = false) BigDecimal interestRate) {
        super(balance, user);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
        this.lastInterestDate = new Date();
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
