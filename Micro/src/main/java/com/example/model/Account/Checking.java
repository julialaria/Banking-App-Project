package com.example.model.Account;

import com.example.enums.Status;
import com.example.model.others.Money;
import com.example.model.others.User;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Checking extends Account {

    @NotNull(message = "A secretKey is required")
    private String secretKey;
    private final BigDecimal minimumBalance = new BigDecimal(250);
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "monthly_maintenance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "monthly_maintenance_currency"))
    })
    private final Money monthlyMaintenanceFee = new Money(new BigDecimal(12));
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date lastMaintenanceFee;

    public Checking(){
        this.lastMaintenanceFee = new Date();
    }

    public Checking(@NotNull(message = "A balance is required") Money balance,
                    @NotNull(message = "A primaryOwner is required") User user,
                    @NotNull(message = "A secretKey is required") String secretKey) {
        super(balance, user);
        this.secretKey = secretKey;
        this.status = Status.ACTIVE;
        this.lastMaintenanceFee = new Date();
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

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }
}
