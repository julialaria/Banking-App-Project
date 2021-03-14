package com.example.controller.interfaces.others;

import com.example.controller.dto.TransactionDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ITransactionController {
    List<TransactionDTO> getAll();
    public void create (TransactionDTO transactionDTO);

}
