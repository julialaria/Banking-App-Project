package com.example.service.interfaces.Accounts;

import com.example.controller.dto.AccountGetDTO;
import com.example.controller.dto.BalanceDTO;
import com.example.enums.Status;
import com.example.model.others.Money;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    public List<AccountGetDTO> findAll();
    public void updateBalance(Long id, Money balance);
    public List<AccountGetDTO> getAllAccountsByUserId(Long id);
    public void deleteAccountById(Long id);
    public void updateStatus(Long id, Status status);
    public AccountGetDTO findAccountById(Long id);
    public void updateAccount (Long id, double balance);
   // public void update (String hashedKey, AccountDTO accountDTO);
}
