package edge.security.services.interfaces;

import edge.dto.TransactionDTO;

import java.util.List;

public interface ITransactionEdgeService {

    public List<TransactionDTO> getAll();
    public void create(TransactionDTO transactionDTO);
}
