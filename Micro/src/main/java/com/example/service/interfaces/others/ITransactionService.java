package com.example.service.interfaces.others;



import com.example.controller.dto.TransactionDTO;

import java.util.List;

public interface ITransactionService {

    List<TransactionDTO> getAll();
    public void create(TransactionDTO transactionDTO);
}
