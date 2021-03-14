package com.example.controller.impl.Accounts;

import com.example.controller.dto.*;
import com.example.controller.interfaces.Accounts.IAccountController;

import com.example.model.Account.Account;
import com.example.model.others.Money;
import com.example.service.interfaces.Accounts.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    private com.example.repository.Accounts.AccountRepository accountRepository;

    @Autowired
    private IAccountService accountService;

    @Autowired
    com.example.repository.others.UserRepository userRepository;

    @GetMapping("/accounts")
    @CrossOrigin
    public List<AccountGetDTO> findAllAccount() {
        return accountService.findAll();
    }

    @GetMapping("/accounts/by-userId/{id}")
    @CrossOrigin
    public List<AccountGetDTO> findAccountsByUserId(@PathVariable Long id) {
        return accountService.getAllAccountsByUserId(id);
    }

    @GetMapping("/accounts/by-id/{id}")
    @CrossOrigin
    public AccountGetDTO findAccountById(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }

    @GetMapping("/balance/by-userId/{id}")
    @CrossOrigin
    public BigDecimal findBalanceByUserId(@PathVariable Long id) {
        return accountRepository.findBalanceByUserId(id);
    }

    @PatchMapping("/updateBalance/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance (@PathVariable Long id, @RequestBody Money balance) {
        accountService.updateBalance(id, balance);
    }

    @Override
    @DeleteMapping("/account/{id}")
    @CrossOrigin
    public void deleteAccountById(@PathVariable Long id) {
         accountService.deleteAccountById(id);
    }

    @Override
    @PatchMapping("/updateStatus/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Long id, @RequestBody @Valid StatusDTO statusDTO) {
        accountService.updateStatus(id, statusDTO.getStatus());
    }

    @Override
    @PutMapping("/updateAccount/{id}")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAccount(@PathVariable Long id, @RequestBody @Valid double balance) {
        accountService.updateAccount(id, balance);
    }

}


