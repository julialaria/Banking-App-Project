package com.example.service.impl.Accounts;

import com.example.controller.dto.CheckingDTO;
import com.example.controller.dto.CheckingGetDTO;
import com.example.controller.dto.TransactionDTO;
import com.example.enums.Status;
import com.example.model.Account.Checking;
import com.example.model.others.Transaction;
import com.example.repository.Accounts.CheckingRepository;
import com.example.repository.others.UserRepository;
import com.example.service.interfaces.Accounts.ICheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CheckingService implements ICheckingService {

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    UserRepository userRepository;


    public List<CheckingGetDTO> findAll() {

        List<Checking> checkingList = checkingRepository.findAll();
        List<CheckingGetDTO> checkingGetDTOList = new ArrayList<>();

        List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
        List<TransactionDTO> sendTransactionsList = new ArrayList<>();
        TransactionDTO transaction = new TransactionDTO();

        for (Checking checking : checkingList) {
            CheckingGetDTO checkingGetDTO = new CheckingGetDTO();
            checkingGetDTO.setBalance(checking.getBalance());
            checkingGetDTO.setDateOfCreation(checking.getDateOfCreation());
            checkingGetDTO.setStatus(checking.getStatus());
            checkingGetDTO.setSecretKey(checking.getSecretKey());
            checkingGetDTO.setLastMaintenanceFee(checking.getLastMaintenanceFee());
            checkingGetDTO.setUser(checking.getUser());

            for (Transaction transactions : checking.getReceivedTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                receivedTransactionsList.add(transaction);
            }

            for (Transaction transactions : checking.getSentTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                sendTransactionsList.add(transaction);
            }

            checkingGetDTO.setSentTransactions(sendTransactionsList);
            checkingGetDTO.setReceivedTransactions(receivedTransactionsList);
            checkingGetDTO.setId(checking.getId());
            checkingGetDTOList.add(checkingGetDTO);

        }
        return checkingGetDTOList;
    }

    public CheckingGetDTO findById(Long id) {

        if (checkingRepository.findById(id).isPresent()) {

            Checking checking = checkingRepository.findById(id).get();
            TransactionDTO transaction = new TransactionDTO();
            List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
            List<TransactionDTO> sendTransactionsList = new ArrayList<>();

            CheckingGetDTO checkingGetDTO = new CheckingGetDTO();
            checkingGetDTO.setId(checking.getId());
            checkingGetDTO.setBalance(checking.getBalance());

            for (Transaction transactions : checking.getReceivedTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                receivedTransactionsList.add(transaction);
            }

            for (Transaction transactions : checking.getSentTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                sendTransactionsList.add(transaction);
            }

            checkingGetDTO.setSentTransactions(sendTransactionsList);
            checkingGetDTO.setReceivedTransactions(receivedTransactionsList);
            checkingGetDTO.setUser(checking.getUser());
            checkingGetDTO.setSecretKey(checking.getSecretKey());
            checkingGetDTO.setStatus(checking.getStatus());
            checkingGetDTO.setLastMaintenanceFee(checking.getLastMaintenanceFee());
            checkingGetDTO.setDateOfCreation(checking.getDateOfCreation());

            return checkingGetDTO;

        }else{
            throw new IllegalArgumentException("The id is not present");
        }
    }

    public List <CheckingGetDTO> findCheckingByUserId(Long id) {

        if (checkingRepository.findCheckingByUserId(id) != null) {

            List <Checking> checkings = checkingRepository.findCheckingByUserId(id);
            TransactionDTO transaction = new TransactionDTO();
            List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
            List<TransactionDTO> sendTransactionsList = new ArrayList<>();



            List<CheckingGetDTO> checkingGetDTOList = new ArrayList<>();

            for(Checking checking : checkings) {
                CheckingGetDTO checkingGetDTO = new CheckingGetDTO();
                checkingGetDTO.setBalance(checking.getBalance());
                checkingGetDTO.setId(checking.getId());


                for (Transaction transactions : checking.getReceivedTransactions()) {
                    transaction.setAmount(transactions.getAmount());
                    transactions.setId(transactions.getId());
                    transactions.setTransactionDate(transactions.getTransactionDate());
                    transactions.setDescription(transactions.getDescription());
                    transactions.setDestinationAccount(transactions.getDestinationAccount());
                    transactions.setOrigenAccount(transactions.getOrigenAccount());
                    receivedTransactionsList.add(transaction);
                }

                for (Transaction transactions : checking.getSentTransactions()) {
                    transaction.setAmount(transactions.getAmount());
                    transactions.setId(transactions.getId());
                    transactions.setTransactionDate(transactions.getTransactionDate());
                    transactions.setDescription(transactions.getDescription());
                    transactions.setDestinationAccount(transactions.getDestinationAccount());
                    transactions.setOrigenAccount(transactions.getOrigenAccount());
                    sendTransactionsList.add(transaction);
                }

                checkingGetDTO.setSentTransactions(sendTransactionsList);
                checkingGetDTO.setReceivedTransactions(receivedTransactionsList);
                checkingGetDTO.setUser(checking.getUser());
                checkingGetDTO.setSecretKey(checking.getSecretKey());
                checkingGetDTO.setStatus(checking.getStatus());
                checkingGetDTO.setLastMaintenanceFee(checking.getLastMaintenanceFee());
                checkingGetDTO.setDateOfCreation(checking.getDateOfCreation());

                checkingGetDTOList.add(checkingGetDTO);
            }



            return checkingGetDTOList;

        }else{
            throw new IllegalArgumentException("The id is not present");
        }
    }


    public void create (CheckingDTO checkingDTO){

        Checking checking = new Checking();
        checking.setSecretKey(checkingDTO.getSecretKey());
        checking.setDateOfCreation(new Date());
        checking.setBalance(checkingDTO.getBalance());
        checking.setStatus(Status.ACTIVE);
        checking.setUser(userRepository.findById(checkingDTO.getPrimaryOwner()).get());
        checkingRepository.save(checking);

    }
}

