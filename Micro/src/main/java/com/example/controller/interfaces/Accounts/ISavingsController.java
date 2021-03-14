package com.example.controller.interfaces.Accounts;

import com.example.controller.dto.SavingsDTO;
import com.example.controller.dto.SavingsGetDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ISavingsController {

    public List<SavingsGetDTO> findAllSavings();
    public SavingsGetDTO findById(Long id);
    public List <SavingsGetDTO> findSavingsByUserId(Long id);
    public void create (SavingsDTO savingsDTO);
    /*Savings store(Savings savings);*/
}
