package edge.controllers.interfaces;

import edge.dto.TransactionDTO;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ITransactionEdgeController {

    public List<TransactionDTO> getAll();
    public void create(TransactionDTO transactionDTO);
}
