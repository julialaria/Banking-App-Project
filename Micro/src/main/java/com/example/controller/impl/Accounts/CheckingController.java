package com.example.controller.impl.Accounts;

import com.example.controller.dto.CheckingDTO;
import com.example.controller.dto.CheckingGetDTO;
import com.example.controller.interfaces.Accounts.ICheckingController;
import com.example.service.interfaces.Accounts.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CheckingController implements ICheckingController {

    @Autowired
    com.example.repository.Accounts.CheckingRepository checkingRepository;

    @Autowired
    ICheckingService checkingService;

    @GetMapping("/checking")
    @CrossOrigin
    public List<CheckingGetDTO> findAllChecking() {
        return checkingService.findAll();
    }

    @GetMapping("/checking/by-id/{id}")
    @CrossOrigin
    public CheckingGetDTO findById(@PathVariable Long id) {
        return checkingService.findById(id);
    }

    @GetMapping("/checking/by-userid/{id}")
    @CrossOrigin
    public List <CheckingGetDTO> findCheckingByUserId(@PathVariable Long id) {
        return checkingService.findCheckingByUserId(id);
    }

    @PostMapping("/create/checking")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CheckingDTO checkingDTO) {
        checkingService.create(checkingDTO);
    }
}
