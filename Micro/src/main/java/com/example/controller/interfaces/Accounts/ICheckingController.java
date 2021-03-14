package com.example.controller.interfaces.Accounts;
import com.example.controller.dto.CheckingDTO;
import com.example.controller.dto.CheckingGetDTO;

import java.util.List;
import java.util.Optional;

public interface ICheckingController {

    public List<CheckingGetDTO> findAllChecking();
    public CheckingGetDTO findById(Long id);
    public List <CheckingGetDTO> findCheckingByUserId(Long idmvn );
    public void create (CheckingDTO checkingDTO);
}
