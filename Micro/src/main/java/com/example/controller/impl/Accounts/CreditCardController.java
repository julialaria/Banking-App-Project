package com.example.controller.impl.Accounts;

import com.example.controller.dto.CheckingGetDTO;
import com.example.controller.dto.CreditCardDTO;
import com.example.controller.dto.CreditCardGetDTO;
import com.example.repository.Accounts.CreditCardRepository;
import com.example.service.interfaces.Accounts.ICreditCardService;
import com.example.controller.interfaces.Accounts.ICreditCardController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CreditCardController implements ICreditCardController {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    ICreditCardService creditCardService;

    @GetMapping("/creditCard")
    @CrossOrigin
    public List<CreditCardGetDTO> findAllCreditCard() {
        return creditCardService.findAll();
    }

    @GetMapping("/creditCard/by-id/{id}")
    @CrossOrigin
    public CreditCardGetDTO findById(@PathVariable Long id) {
        return creditCardService.findById(id);
    }

    @GetMapping("/creditCard/by-userid/{id}")
    @CrossOrigin
    public List <CreditCardGetDTO> findCheckingByUserId(@PathVariable Long id) {
        return creditCardService.findCheckingByUserId(id);
    }


    @PostMapping("/create/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestBody @Valid CreditCardDTO creditCardDTO) {
        creditCardService.create(creditCardDTO);
    }

}
