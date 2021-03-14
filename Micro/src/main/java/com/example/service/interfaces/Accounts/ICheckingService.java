package com.example.service.interfaces.Accounts;

import com.example.controller.dto.CheckingDTO;
import com.example.controller.dto.CheckingGetDTO;

import java.util.List;
import java.util.Optional;

public interface ICheckingService {

    public List<CheckingGetDTO> findAll();

    public CheckingGetDTO findById(Long id);

    public void create(CheckingDTO checkingDTO);

    List <CheckingGetDTO> findCheckingByUserId(Long id);
}
