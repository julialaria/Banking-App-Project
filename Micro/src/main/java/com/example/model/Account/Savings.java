package com.example.model.Account;


import com.example.enums.Status;
import com.example.model.others.Money;
import com.example.model.others.User;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Savings extends Account {
    @NotNull (message = "A secretKey is required")
    private String secretKey;
    @Enumerated(EnumType.STRING)
    private Status status;
    @DecimalMax(value = "1000", message = "The minimumBalance must be less than 1000")
    @DecimalMin(value = "100", message = "The minimumBalance must be greater then 100")
    private BigDecimal minimumBalance = new BigDecimal(1000);
    private final BigDecimal interestRate = new BigDecimal((0.1)).setScale(2, RoundingMode.HALF_UP);
    private Date lastInterestDate;

    public Savings(){
        this.lastInterestDate = new Date();

    }

    public Savings(@NotNull(message = "A balance is required") Money balance,
                   @NotNull(message = "A primaryOwner is required") User user,
                   @NotNull(message = "A secretKey is required") String secretKey) {
        super(balance,user);
        this.lastInterestDate=new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
    }


    public Savings(@NotNull(message = "A balance is required")Money balance,
                   @NotNull(message = "A primaryOwner is required")User user,
                   @NotNull(message = "A secretKey is required")String secretKey,
                   @DecimalMin(value = "0.01")
                   @DecimalMax(value = "0.5")
                   BigDecimal minimumBalance, Date dateOfCreation) {

        super(balance, user);
        this.lastInterestDate=new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.minimumBalance = minimumBalance;

    }

    public Savings(@NotNull(message = "A balance is required")Money balance,
                   @NotNull(message = "A primaryOwner is required") User user,
                   @NotNull(message = "A secretKey is required")String secretKey,
                   @DecimalMax(value = "1000", message = "The minimumBalance must be less than 1000")
                   @DecimalMin(value = "100", message = "The minimumBalance must be greater then 100") BigDecimal minimumBalance

                   ) {
        super(balance, user);
        this.lastInterestDate=new Date();
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.minimumBalance = minimumBalance;


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

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public Date getLastInterestDate() {
        return lastInterestDate;
    }

    public void setLastInterestDate(Date lastInterestDate) {
        this.lastInterestDate = lastInterestDate;
    }
}

