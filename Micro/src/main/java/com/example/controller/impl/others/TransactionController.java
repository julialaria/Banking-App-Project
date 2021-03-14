package com.example.controller.impl.others;


import com.example.controller.dto.AccountGetDTO;
import com.example.controller.dto.TransactionDTO;
import com.example.controller.interfaces.others.ITransactionController;

import com.example.repository.others.TransactionRepository;
import com.example.service.interfaces.others.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TransactionController implements ITransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    ITransactionService transactionService;

    @Override
    @GetMapping("/transactions")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> getAll() {
        return transactionService.getAll();
    }

    @PostMapping("/transaction")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid TransactionDTO transactionDTO) {
        transactionService.create(transactionDTO);
    }

}
