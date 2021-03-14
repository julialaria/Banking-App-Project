package com.example.controller.interfaces.Accounts;
import com.example.controller.dto.CreditCardDTO;
import com.example.controller.dto.CreditCardGetDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ICreditCardController {

    public List<CreditCardGetDTO> findAllCreditCard();

    public CreditCardGetDTO findById(Long id);

    public List <CreditCardGetDTO> findCheckingByUserId(Long id);

    /*CreditCard store (CreditCard creditCard);*/
    public void create (CreditCardDTO creditCardDTO);
}
