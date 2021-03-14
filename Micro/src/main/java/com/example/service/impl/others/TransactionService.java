package com.example.service.impl.others;


import com.example.controller.dto.TransactionDTO;
import com.example.enums.Status;
import com.example.model.Account.Account;
import com.example.model.Account.Checking;
import com.example.model.Account.Savings;
import com.example.model.others.Money;
import com.example.model.others.Transaction;
import com.example.repository.Accounts.AccountRepository;
import com.example.repository.Accounts.CheckingRepository;
import com.example.repository.Accounts.SavingsRepository;
import com.example.repository.others.TransactionRepository;
import com.example.service.interfaces.others.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CheckingRepository checkingRepository;


    @Override
    public List<TransactionDTO> getAll() {

        List<TransactionDTO> transactionDTOList = new ArrayList<>();


        List <Transaction> transactionList = transactionRepository.findAll();

        for (Transaction transaction : transactionList){
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setTransactionDate(transaction.getTransactionDate());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setDestinationAccountId(transaction.getDestinationAccount().getId());
            transactionDTO.setOrigenAccountId(transaction.getOrigenAccount().getId());
            transactionDTO.setNameOwnerDestinationAccount(transaction.getDestinationAccount().getUser().getUsername());
            transactionDTOList.add(transactionDTO);
        }

        return transactionDTOList;
    }

    public void create (TransactionDTO transactionDTO) {
        //busco si existe el id
        Optional<Account> originAccountOp = accountRepository.findById(transactionDTO.getOrigenAccountId());
        Optional<Account> destinationAccountOp = accountRepository.findById(transactionDTO.getDestinationAccountId());

        if (originAccountOp.isPresent() && destinationAccountOp.isPresent()) {

            //guardo las cuentas, origin y destination
            Account originAccount = originAccountOp.get();
            Account destinationAccount = destinationAccountOp.get();

            //Origin account
            if(originAccount instanceof Checking){
                if(((Checking) originAccount).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Origin account is frozen");
                }
            }


            if(originAccount instanceof Savings){
                if(((Savings) originAccount).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Origin account is frozen");
                }
            }
            // Destination account
            if(destinationAccount instanceof Checking){
                if(((Checking) destinationAccount).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Destination account is frozen");
                }

            }
            if(destinationAccount instanceof Savings){
                if(((Savings) destinationAccount).getStatus().equals(Status.FROZEN)){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Destination account is frozen");
                }
            }

            List<Transaction> transactions = originAccount.getSentTransactions();
            if (transactions.size() >= 2) {
                Transaction lastTransaction = transactions.get(transactions.size() - 1);
                Long secondsBetweenTransactions = (transactionDTO.getTransactionDate().getTime() -
                        lastTransaction.getTransactionDate().getTime()) / 1000;

                Integer last24hTransactions = transactionRepository.findTransactionsLast24h(originAccount.getId());
                Integer maxHistoric24hTransactions = transactionRepository.findMaxTransactions24hPeriod(originAccount.getId());

                if (last24hTransactions==null){
                    last24hTransactions=0;
                }

                if (maxHistoric24hTransactions==null){
                    maxHistoric24hTransactions=transactions.size();
                }

                if (secondsBetweenTransactions <= 1 || last24hTransactions > 1.5 * maxHistoric24hTransactions) {
                    if (originAccount instanceof Checking) {
                        ((Checking) originAccount).setStatus(Status.FROZEN);
                        checkingRepository.save((Checking) originAccount);
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Fraud detection, " +
                                "the origin account will be frozen until check");
                    }

                    if (originAccount instanceof Savings) {
                        ((Savings) originAccount).setStatus(Status.FROZEN);
                        savingsRepository.save((Savings) originAccount);
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Fraud detection activated, " +
                                "the origin account will be frozen until check");
                    }
                }
            }

            //guardo el amount que quiera transferir, el nombre del propietario de la cuenta destino, el username y password que me meten por postman,
            //y luego el username y password de la cuenta origen

            Money amount = transactionDTO.getAmount();
            String nameOwnerDestinationAccount = transactionDTO.getNameOwnerDestinationAccount();
            String userName = originAccount.getUser().getUsername();
            String password = originAccount.getUser().getPassword();

            //guardo el balance del origin account
            Money auxBalance = new Money(originAccount.getBalance().getAmount());

            //guardo en un boolean la comparación entre el username y password. En otro boolean guardo la comparación entre el name del account y el que meto por postman
            //El tercer boolean guarda la comprobación de si al realizar la transferencia, mi balance es mayo o igual a 0
            //Boolean userBool = userName.equals(user.getUsername()) && password.equals(user.getPassword());
            Boolean nameBool = destinationAccount.getUser().getUsername().equals(nameOwnerDestinationAccount);
            Boolean enoughBalance = auxBalance.decreaseAmount(amount).compareTo(new BigDecimal("0.00"))>-1;

            //si es correcto, hago la transferencia
            if (nameBool && enoughBalance) {

                //si mi cuenta de origen es del tipo saving, creo un saving para acceder a su minimum balance.
                if (originAccount instanceof Savings) {
                    Savings saving = (Savings) originAccount;

                    //si al realizar la transferencia me quedo por debajo del minimumbalance, aplico el panaltyfee
                    if (originAccount.getBalance().decreaseAmount(amount).compareTo(saving.getMinimumBalance())<0) {
                        originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(saving.getPenaltyFee())));
                    }

                    //lo anterior pero en el caso de checking
                }else if (originAccount instanceof Checking) {
                    Checking checking = (Checking) originAccount;

                    if (originAccount.getBalance().decreaseAmount(amount).compareTo(checking.getMinimumBalance())<0){
                        originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(checking.getPenaltyFee())));
                    }

                }else{

                    //para cualquier otro tipo de cuenta no hay minimumbalance, simplemente hago la transferencia
                    originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(amount)));
                }

                //incremento el balance d ela destination account
                destinationAccount.setBalance(new Money(destinationAccount.getBalance().increaseAmount(amount)));

                //creo una transferencia
                Transaction transaction = new Transaction();
                transaction.setDescription(transactionDTO.getDescription());
                transaction.setAmount(transactionDTO.getAmount());
                transaction.setTransactionDate(new Date());
                transaction.setOrigenAccount(originAccount);
                transaction.setDestinationAccount(destinationAccount);

                transactionRepository.save(transaction);

          //  } else if (!userBool) {

            //    throw new  ResponseStatusException(HttpStatus.BAD_REQUEST,"Incorrect username or password");

            } else if (!nameBool){

                throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "The given name doest not match any account");

            }else{

                throw new ResponseStatusException( HttpStatus.BAD_REQUEST,"There is not enough money to make de transaction");
            }

        }else{

            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "The given Account id doest not match any account");
        }
    }
}


