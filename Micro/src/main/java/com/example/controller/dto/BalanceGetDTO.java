package com.example.controller.dto;

import java.math.BigDecimal;

public class BalanceGetDTO {

    public BalanceGetDTO(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    private BigDecimal balance;


}
