package com.example.controller.impl.Accounts;

import com.example.controller.dto.CreditCardGetDTO;
import com.example.controller.dto.SavingsDTO;
import com.example.controller.dto.SavingsGetDTO;
import com.example.controller.interfaces.Accounts.ISavingsController;
import com.example.repository.Accounts.SavingsRepository;
import com.example.service.interfaces.Accounts.ISavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class SavingsController implements ISavingsController {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    ISavingsService savingsService;

    @GetMapping("/savings")
    @CrossOrigin
    public List<SavingsGetDTO> findAllSavings() {
        return savingsService.findAll();
    }

    @GetMapping("/savings/by-id/{id}")
    @CrossOrigin
    public SavingsGetDTO findById(@PathVariable Long id) {
        return savingsService.findById(id);
    }

    @GetMapping("/savings/by-userid/{id}")
    @CrossOrigin
    public List <SavingsGetDTO> findSavingsByUserId(@PathVariable Long id) {
        return savingsService.findSavingsByUserId(id);
    }

    @PostMapping("/create/savings")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid SavingsDTO savingsDTO) {
        savingsService.create(savingsDTO);
    }

}
