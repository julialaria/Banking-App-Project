package com.example.service.interfaces.Accounts;


import com.example.controller.dto.SavingsDTO;
import com.example.controller.dto.SavingsGetDTO;

import java.util.List;

public interface ISavingsService {

    public void create (SavingsDTO savingsDTO);
    public SavingsGetDTO findById(Long id);
    public List <SavingsGetDTO> findSavingsByUserId(Long id);
    public List<SavingsGetDTO> findAll();

}
