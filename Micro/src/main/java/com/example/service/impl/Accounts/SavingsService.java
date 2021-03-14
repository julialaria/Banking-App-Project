package com.example.service.impl.Accounts;

import com.example.controller.dto.CreditCardGetDTO;
import com.example.controller.dto.SavingsDTO;
import com.example.controller.dto.SavingsGetDTO;
import com.example.controller.dto.TransactionDTO;
import com.example.enums.Status;
import com.example.model.Account.CreditCard;
import com.example.model.Account.Savings;
import com.example.model.others.Transaction;
import com.example.repository.Accounts.SavingsRepository;
import com.example.repository.others.UserRepository;
import com.example.service.interfaces.Accounts.ISavingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SavingsService implements ISavingsService {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    UserRepository userRepository;


    public List<SavingsGetDTO> findAll() {

        List<Savings> savingsList = savingsRepository.findAll();
        List<SavingsGetDTO> savingsGetDTOList = new ArrayList<>();


        List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
        List<TransactionDTO> sendTransactionsList = new ArrayList<>();
        TransactionDTO transaction = new TransactionDTO();

        for (Savings savings : savingsList) {
            SavingsGetDTO savingsGetDTO = new SavingsGetDTO();
            savingsGetDTO.setId(savings.getId());
            savingsGetDTO.setBalance(savings.getBalance());
            savingsGetDTO.setDateOfCreation(savings.getDateOfCreation());
            savingsGetDTO.setUser(savings.getUser());
            savingsGetDTO.setSecretKey(savings.getSecretKey());
            savingsGetDTO.setInterestRate(savings.getInterestRate());
            savingsGetDTO.setLastInterestDate(savings.getLastInterestDate());
            savingsGetDTO.setMinimumBalance(savings.getMinimumBalance());
            savingsGetDTO.setStatus(savings.getStatus());

            for (Transaction transactions : savings.getReceivedTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                receivedTransactionsList.add(transaction);
            }

            for (Transaction transactions : savings.getSentTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                sendTransactionsList.add(transaction);
            }

            savingsGetDTO.setSentTransactions(sendTransactionsList);
            savingsGetDTO.setReceivedTransactions(receivedTransactionsList);
            savingsGetDTOList.add(savingsGetDTO);

        }
        return savingsGetDTOList;
    }


    public SavingsGetDTO findById(Long id) {

        if (savingsRepository.findById(id).isPresent()) {

            Savings savings = savingsRepository.findById(id).get();
            TransactionDTO transaction = new TransactionDTO();
            List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
            List<TransactionDTO> sendTransactionsList = new ArrayList<>();

            SavingsGetDTO savingsGetDTO = new SavingsGetDTO();
            savingsGetDTO.setId(savings.getId());
            savingsGetDTO.setBalance(savingsGetDTO.getBalance());
            savingsGetDTO.setDateOfCreation(savings.getDateOfCreation());
            savingsGetDTO.setUser(savings.getUser());
            savingsGetDTO.setSecretKey(savings.getSecretKey());
            savingsGetDTO.setInterestRate(savings.getInterestRate());
            savingsGetDTO.setLastInterestDate(savings.getLastInterestDate());
            savingsGetDTO.setMinimumBalance(savings.getMinimumBalance());
            savingsGetDTO.setStatus(savings.getStatus());

            for (Transaction transactions : savings.getReceivedTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                receivedTransactionsList.add(transaction);
            }

            for (Transaction transactions : savings.getSentTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                sendTransactionsList.add(transaction);
            }

            savingsGetDTO.setSentTransactions(sendTransactionsList);
            savingsGetDTO.setReceivedTransactions(receivedTransactionsList);

            return savingsGetDTO;

        } else {
            throw new IllegalArgumentException("The id is not present");
        }
    }

    public List <SavingsGetDTO> findSavingsByUserId(Long id) {

        if (savingsRepository.findSavingsByUserId(id) != null) {

            List <Savings> savings = savingsRepository.findSavingsByUserId(id);
            TransactionDTO transaction = new TransactionDTO();
            List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
            List<TransactionDTO> sendTransactionsList = new ArrayList<>();

            List<SavingsGetDTO> savingsGetDTOList = new ArrayList<>();

            for(Savings saving : savings) {
                SavingsGetDTO savingsGetDTO = new SavingsGetDTO();
                savingsGetDTO.setBalance(saving.getBalance());
                savingsGetDTO.setId(saving.getId());


                for (Transaction transactions : saving.getReceivedTransactions()) {
                    transaction.setAmount(transactions.getAmount());
                    transactions.setId(transactions.getId());
                    transactions.setTransactionDate(transactions.getTransactionDate());
                    transactions.setDescription(transactions.getDescription());
                    transactions.setDestinationAccount(transactions.getDestinationAccount());
                    transactions.setOrigenAccount(transactions.getOrigenAccount());
                    receivedTransactionsList.add(transaction);
                }

                for (Transaction transactions : saving.getSentTransactions()) {
                    transaction.setAmount(transactions.getAmount());
                    transactions.setId(transactions.getId());
                    transactions.setTransactionDate(transactions.getTransactionDate());
                    transactions.setDescription(transactions.getDescription());
                    transactions.setDestinationAccount(transactions.getDestinationAccount());
                    transactions.setOrigenAccount(transactions.getOrigenAccount());
                    sendTransactionsList.add(transaction);
                }

                savingsGetDTO.setSentTransactions(sendTransactionsList);
                savingsGetDTO.setReceivedTransactions(receivedTransactionsList);
                savingsGetDTO.setUser(saving.getUser());
                savingsGetDTO.setDateOfCreation(saving.getDateOfCreation());
                savingsGetDTO.setSecretKey(saving.getSecretKey());
                savingsGetDTO.setStatus(saving.getStatus());
                savingsGetDTO.setLastInterestDate(saving.getLastInterestDate());
                savingsGetDTO.setMinimumBalance(saving.getMinimumBalance());
                savingsGetDTO.setInterestRate(saving.getInterestRate());

               savingsGetDTOList.add(savingsGetDTO);

            }

            return savingsGetDTOList;

        }else{

            throw new IllegalArgumentException("The id is not present");
        }
    }


        public void create (SavingsDTO savingsDTO){

            Savings savings = new Savings();
            savings.setSecretKey(savingsDTO.getSecretKey());
            savings.setMinimumBalance(savingsDTO.getMinimumBalance());
            savings.setStatus(Status.ACTIVE);
            savings.setBalance(savingsDTO.getBalance());
            savings.setDateOfCreation(new Date());
            savings.setUser(userRepository.findById(savingsDTO.getPrimaryOwner()).get());
            savingsRepository.save(savings);
        }
    }


