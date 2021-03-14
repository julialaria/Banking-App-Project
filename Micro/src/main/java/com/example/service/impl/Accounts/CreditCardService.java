package com.example.service.impl.Accounts;


import com.example.controller.dto.CheckingGetDTO;
import com.example.controller.dto.CreditCardDTO;
import com.example.controller.dto.CreditCardGetDTO;
import com.example.controller.dto.TransactionDTO;
import com.example.model.Account.Checking;
import com.example.model.Account.CreditCard;
import com.example.model.others.Transaction;
import com.example.repository.Accounts.CreditCardRepository;
import com.example.repository.others.UserRepository;
import com.example.service.interfaces.Accounts.ICreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CreditCardService implements ICreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    UserRepository userRepository;


    public List<CreditCardGetDTO> findAll(){


        List<CreditCard> creditCardList = creditCardRepository.findAll();
        List<CreditCardGetDTO> creditCardGetDTOList = new ArrayList<>();
        List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
        List<TransactionDTO> sendTransactionsList = new ArrayList<>();
        TransactionDTO transaction = new TransactionDTO();

        for (CreditCard creditCard : creditCardList) {
            CreditCardGetDTO creditCardGetDTO = new CreditCardGetDTO();
            creditCardGetDTO.setId(creditCard.getId());
            creditCardGetDTO.setBalance(creditCard.getBalance());
            creditCardGetDTO.setDateOfCreation(creditCard.getDateOfCreation());
            creditCardGetDTO.setUser(creditCard.getUser());
            creditCardGetDTO.setCreditLimit(creditCard.getCreditLimit());
            creditCardGetDTO.setInterestRate(creditCard.getInterestRate());
            creditCardGetDTO.setLastInterestDate(creditCard.getLastInterestDate());

            for (Transaction transactions : creditCard.getReceivedTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                receivedTransactionsList.add(transaction);
            }

            for (Transaction transactions : creditCard.getSentTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                sendTransactionsList.add(transaction);
            }

            creditCardGetDTO.setSentTransactions(sendTransactionsList);
            creditCardGetDTO.setReceivedTransactions(receivedTransactionsList);

            creditCardGetDTOList.add(creditCardGetDTO);

        }

        return creditCardGetDTOList;

    }


    public CreditCardGetDTO findById(Long id) {

        if (creditCardRepository.findById(id).isPresent()) {

            CreditCard creditCard= creditCardRepository.findById(id).get();
            TransactionDTO transaction = new TransactionDTO();
            List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
            List<TransactionDTO> sendTransactionsList = new ArrayList<>();

            CreditCardGetDTO creditCardGetDTO = new CreditCardGetDTO();
            creditCardGetDTO.setId(creditCard.getId());
            creditCardGetDTO.setBalance(creditCard.getBalance());
            creditCardGetDTO.setDateOfCreation(creditCard.getDateOfCreation());
            creditCardGetDTO.setUser(creditCard.getUser());
            creditCardGetDTO.setCreditLimit(creditCard.getCreditLimit());
            creditCardGetDTO.setInterestRate(creditCard.getInterestRate());
            creditCardGetDTO.setLastInterestDate(creditCard.getLastInterestDate());


            for (Transaction transactions : creditCard.getReceivedTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                receivedTransactionsList.add(transaction);
            }

            for (Transaction transactions : creditCard.getSentTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                sendTransactionsList.add(transaction);
            }

            creditCardGetDTO.setSentTransactions(sendTransactionsList);
            creditCardGetDTO.setReceivedTransactions(receivedTransactionsList);


            return creditCardGetDTO;

        }else{
            throw new IllegalArgumentException("The id is not present");
        }

    }

    public List <CreditCardGetDTO> findCheckingByUserId(Long id) {

        if (creditCardRepository.findCheckingByUserId(id) != null) {

            List <CreditCard> creditCards = creditCardRepository.findCheckingByUserId(id);
            TransactionDTO transaction = new TransactionDTO();
            List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
            List<TransactionDTO> sendTransactionsList = new ArrayList<>();

            List<CreditCardGetDTO> creditCardGetDTOList = new ArrayList<>();

            for(CreditCard creditCard : creditCards) {
                CreditCardGetDTO creditCardGetDTO = new CreditCardGetDTO();
                creditCardGetDTO.setBalance(creditCard.getBalance());
                creditCardGetDTO.setId(creditCard.getId());


                for (Transaction transactions : creditCard.getReceivedTransactions()) {
                    transaction.setAmount(transactions.getAmount());
                    transactions.setId(transactions.getId());
                    transactions.setTransactionDate(transactions.getTransactionDate());
                    transactions.setDescription(transactions.getDescription());
                    transactions.setDestinationAccount(transactions.getDestinationAccount());
                    transactions.setOrigenAccount(transactions.getOrigenAccount());
                    receivedTransactionsList.add(transaction);
                }

                for (Transaction transactions : creditCard.getSentTransactions()) {
                    transaction.setAmount(transactions.getAmount());
                    transactions.setId(transactions.getId());
                    transactions.setTransactionDate(transactions.getTransactionDate());
                    transactions.setDescription(transactions.getDescription());
                    transactions.setDestinationAccount(transactions.getDestinationAccount());
                    transactions.setOrigenAccount(transactions.getOrigenAccount());
                    sendTransactionsList.add(transaction);
                }

                creditCardGetDTO.setSentTransactions(sendTransactionsList);
                creditCardGetDTO.setReceivedTransactions(receivedTransactionsList);
                creditCardGetDTO.setUser(creditCard.getUser());
                creditCardGetDTO.setDateOfCreation(creditCard.getDateOfCreation());
                creditCardGetDTO.setCreditLimit(creditCard.getCreditLimit());
                creditCardGetDTO.setInterestRate(creditCard.getInterestRate());
                creditCardGetDTO.setLastInterestDate(creditCard.getLastInterestDate());

                creditCardGetDTOList.add(creditCardGetDTO);
            }

            return creditCardGetDTOList;

        }else{

            throw new IllegalArgumentException("The id is not present");
        }
    }



    public void create (CreditCardDTO creditCardDTO){
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditLimit(creditCardDTO.getCreditLimit());
        creditCard.setInterestRate(creditCardDTO.getInterestRate());
        creditCard.setBalance(creditCardDTO.getBalance());
        creditCard.setDateOfCreation(new Date());
        creditCard.setUser(userRepository.findById(creditCardDTO.getPrimaryOwner()).get());
        creditCardRepository.save(creditCard);
    }
}
