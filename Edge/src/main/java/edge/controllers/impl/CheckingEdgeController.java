package edge.controllers.impl;

import edge.controllers.interfaces.ICheckingEdgeController;
import edge.dto.CheckingDTO;
import edge.dto.CheckingGetDTO;
import edge.security.services.impl.CheckingEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class CheckingEdgeController implements ICheckingEdgeController {

    @Autowired
    private CheckingEdgeService checkingEdgeService;

    @GetMapping("/checking")
    @CrossOrigin
    public List<CheckingGetDTO> findAllChecking() {
        return checkingEdgeService.findAll();
    }

    @GetMapping("/checking/by-id/{id}")
    @CrossOrigin
    public CheckingGetDTO findById(@PathVariable Long id) {
        return checkingEdgeService.findById(id);
    }

    @GetMapping("/checking/by-userid/{id}")
    @CrossOrigin
    public List <CheckingGetDTO> findCheckingByUserId(@PathVariable Long id) {
        return checkingEdgeService.findCheckingByUserId(id);
    }

    @PostMapping("/create/checking")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CheckingDTO checkingDTO) {
        checkingEdgeService.create(checkingDTO);
    }

}
