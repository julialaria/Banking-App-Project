package com.example.service.interfaces.Accounts;


import com.example.controller.dto.CreditCardDTO;
import com.example.controller.dto.CreditCardGetDTO;

import java.util.List;

public interface ICreditCardService {

    public List<CreditCardGetDTO> findAll();

    public CreditCardGetDTO findById(Long id);

    public List <CreditCardGetDTO> findCheckingByUserId(Long id);

    /*CreditCard store (CreditCard creditCard);*/
    public void create(CreditCardDTO creditCardDTO);
}
