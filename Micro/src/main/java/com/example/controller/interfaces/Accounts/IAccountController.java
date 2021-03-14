package com.example.controller.interfaces.Accounts;

import com.example.controller.dto.AccountGetDTO;
import com.example.controller.dto.BalanceDTO;
import com.example.controller.dto.BalanceGetDTO;
import com.example.controller.dto.StatusDTO;
import com.example.model.Account.Account;
import com.example.model.others.Money;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

public interface IAccountController {

    public List<AccountGetDTO> findAllAccount();

    public List<AccountGetDTO> findAccountsByUserId(Long id);

    public void updateBalance (Long id, Money balance);

    public BigDecimal findBalanceByUserId(@PathVariable Long id);

    public void deleteAccountById(@PathVariable Long id);

    void updateStatus(Long id, StatusDTO statusDTO);

    public AccountGetDTO findAccountById(@PathVariable Long id);

    public void updateAccount(Long id, double balance);

}
