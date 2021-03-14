package edge.controllers.impl;

import edge.controllers.interfaces.ISavingsEdgeController;
import edge.dto.CreditCardGetDTO;
import edge.dto.SavingsDTO;
import edge.dto.SavingsGetDTO;
import edge.security.services.impl.SavingsEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class SavingsEdgeController implements ISavingsEdgeController {

    @Autowired
    private SavingsEdgeService savingsEdgeService;

    @GetMapping("/savings")
    @CrossOrigin
    public List<SavingsGetDTO> findAllSavings() {
        return savingsEdgeService.findAll();
    }

    @GetMapping("/savings/by-id/{id}")
    @CrossOrigin
    public SavingsGetDTO findById(@PathVariable Long id) {
        return savingsEdgeService.findById(id);
    }


    @GetMapping("/savings/by-userid/{id}")
    @CrossOrigin
    public List <SavingsGetDTO> findSavingsByUserId(@PathVariable Long id) {
        return savingsEdgeService.findSavingsByUserId(id);
    }

    @PostMapping("/create/savings")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid SavingsDTO savingsDTO) {
        savingsEdgeService.create(savingsDTO);
    }
}
