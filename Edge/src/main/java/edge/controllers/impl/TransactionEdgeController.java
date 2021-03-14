package edge.controllers.impl;

import edge.client.TransactionClient;
import edge.controllers.interfaces.ITransactionEdgeController;
import edge.dto.TransactionDTO;
import edge.security.services.impl.TransactionEdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TransactionEdgeController implements ITransactionEdgeController {

    @Autowired
    private TransactionEdgeService transactionEdgeService;


    @GetMapping("/transactions")
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> getAll() {
        return transactionEdgeService.getAll();
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid TransactionDTO transactionDTO) {
        transactionEdgeService.create(transactionDTO);
    }

}
