package edge.controllers.impl;

import edge.controllers.interfaces.ICreditCardEdgeController;
import edge.dto.CheckingGetDTO;
import edge.dto.CreditCardDTO;
import edge.dto.CreditCardGetDTO;
import edge.security.services.impl.CreditCardEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class CreditCardEdgeController implements ICreditCardEdgeController {

    @Autowired
    private CreditCardEdgeService creditCardEdgeService;

    @GetMapping("/creditCard")
    @CrossOrigin
    public List<CreditCardGetDTO> findAllCreditCard() {
        return creditCardEdgeService.findAll();
    }

    @GetMapping("/creditCard/by-id/{id}")
    @CrossOrigin
    public CreditCardGetDTO findById(@PathVariable Long id) {
        return creditCardEdgeService.findById(id);
    }

    @GetMapping("/creditCard/by-userid/{id}")
    @CrossOrigin
    public List <CreditCardGetDTO> findCreditCardByUserId(@PathVariable Long id) {
        return creditCardEdgeService.findCreditCardByUserId(id);
    }

    @PostMapping("/create/creditCard")
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void create(@RequestBody @Valid CreditCardDTO creditCardDTO) {
        creditCardEdgeService.create(creditCardDTO);
    }
}
