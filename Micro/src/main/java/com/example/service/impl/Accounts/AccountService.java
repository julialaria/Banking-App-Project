package com.example.service.impl.Accounts;

import com.example.controller.dto.AccountGetDTO;
import com.example.controller.dto.BalanceDTO;
import com.example.controller.dto.CheckingGetDTO;
import com.example.controller.dto.TransactionDTO;
import com.example.enums.Status;
import com.example.model.Account.Account;
import com.example.model.Account.Checking;
import com.example.model.Account.CreditCard;
import com.example.model.Account.Savings;
import com.example.model.others.Money;
import com.example.model.others.Transaction;
import com.example.model.others.User;
import com.example.repository.Accounts.AccountRepository;
import com.example.repository.Accounts.CheckingRepository;
import com.example.repository.Accounts.CreditCardRepository;
import com.example.repository.Accounts.SavingsRepository;
import com.example.repository.others.UserRepository;
import com.example.service.interfaces.Accounts.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    UserRepository userRepository;

    public List<AccountGetDTO> findAll() {

        List<Account> accountList = accountRepository.findAll();
        List<AccountGetDTO> accountGetDTOList = new ArrayList<>();

        List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
        List<TransactionDTO> sendTransactionsList = new ArrayList<>();
        TransactionDTO transaction = new TransactionDTO();

        for (Account account : accountList) {
            AccountGetDTO accountGetDTO = new AccountGetDTO();
            accountGetDTO.setBalance(account.getBalance());
            accountGetDTO.setDateOfCreation(account.getDateOfCreation());
            accountGetDTO.setUser(account.getUser());

            for (Transaction transactions : account.getReceivedTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                receivedTransactionsList.add(transaction);
            }

            for (Transaction transactions : account.getSentTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                sendTransactionsList.add(transaction);
            }

            accountGetDTO.setSentTransactions(sendTransactionsList);
            accountGetDTO.setReceivedTransactions(receivedTransactionsList);
            accountGetDTO.setId(account.getId());
            accountGetDTOList.add(accountGetDTO);

        }
        return accountGetDTOList;
    }



    public List<AccountGetDTO> getAllAccountsByUserId(Long id) {

        User user = userRepository.findById(id).get();
        List<Account> accounts = accountRepository.findByUser(user);

        for (Account account : accounts){
            if (account instanceof Savings){
                Savings saving = (Savings) account;
                Date dateNow = new Date();
                int years=0;
                saving.getLastInterestDate().setYear(saving.getLastInterestDate().getYear()+1);
                Date lastDate = saving.getLastInterestDate();
                while(lastDate.before(dateNow)){
                    years++;
                    lastDate.setYear(lastDate.getYear()+1);
                }
                lastDate.setYear(lastDate.getYear()-1);
                saving.setLastInterestDate(lastDate);
                BigDecimal interest = new BigDecimal(saving.getInterestRate().doubleValue());
                interest = interest.add(new BigDecimal(1));
                interest = interest.pow(years);

                saving.setBalance(new Money(saving.getBalance().getAmount().multiply(interest)));
                saving.setLastInterestDate(lastDate);
                savingsRepository.save(saving);
            }

            if (account instanceof CreditCard){
                CreditCard creditCard = (CreditCard) account;
                Date dateNow = new Date();
                int months = 0;
                creditCard.getLastInterestDate().setMonth(creditCard.getLastInterestDate().getMonth()+1);
                Date lastDate = creditCard.getLastInterestDate();
                while(lastDate.before(dateNow)){
                    months++;
                    lastDate.setMonth(lastDate.getMonth()+1);
                }
                lastDate.setMonth(lastDate.getMonth()-1);
                creditCard.setLastInterestDate(lastDate);
                BigDecimal interest = new BigDecimal(creditCard.getInterestRate().doubleValue());
                interest = interest.divide(new BigDecimal(12),2,RoundingMode.HALF_UP);
                interest = interest.add (new BigDecimal(1));
                interest = interest.pow (months);

                creditCard.setBalance(new Money(creditCard.getBalance().getAmount().multiply(interest)));
                creditCard.setLastInterestDate(lastDate);
                creditCardRepository.save(creditCard);
            }

            if (account instanceof  Checking){
                Checking checking  = (Checking) account;
                Date dateNow = new Date();
                int months = 0;
                checking.getLastMaintenanceFee().setMonth(checking.getLastMaintenanceFee().getMonth()+1);
                Date lastDate = checking.getLastMaintenanceFee();
                while(lastDate.before(dateNow)){
                    months++;
                    lastDate.setMonth(lastDate.getMonth()+1);
                }
                lastDate.setMonth(lastDate.getMonth()-1);
                checking.setLastMaintenanceFee(lastDate);
                BigDecimal montlyFee = checking.getMonthlyMaintenanceFee().getAmount();
                montlyFee = montlyFee.multiply(new BigDecimal(months));
                checking.setBalance(new Money(checking.getBalance().decreaseAmount(new Money(montlyFee))));
                checking.setLastMaintenanceFee(lastDate);
                checkingRepository.save(checking);
            }

        }

        List<Account> accountsList = accountRepository.findAll();
        List<AccountGetDTO> accountGetDTOList = new ArrayList<>();


        for (Account account : accountsList){
            AccountGetDTO accountGetDTO = new AccountGetDTO();
            accountGetDTO.setId(account.getId());
            accountGetDTO.setBalance(account.getBalance());
            accountGetDTO.setUser(account.getUser());
            accountGetDTO.setDateOfCreation(account.getDateOfCreation());

            List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
            List<TransactionDTO> sendTransactionsList = new ArrayList<>();
            TransactionDTO transaction = new TransactionDTO();

            for (Transaction transactions : account.getReceivedTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                receivedTransactionsList.add(transaction);
            }

            for (Transaction transactions : account.getSentTransactions()) {
                transaction.setAmount(transactions.getAmount());
                transactions.setId(transactions.getId());
                transactions.setTransactionDate(transactions.getTransactionDate());
                transactions.setDescription(transactions.getDescription());
                transactions.setDestinationAccount(transactions.getDestinationAccount());
                transactions.setOrigenAccount(transactions.getOrigenAccount());
                sendTransactionsList.add(transaction);
            }
            accountGetDTO.setReceivedTransactions(receivedTransactionsList);
            accountGetDTO.setSentTransactions(sendTransactionsList);

            accountGetDTOList.add(accountGetDTO);
        }

        return accountGetDTOList;
    }

    public BalanceDTO findBalanceByUserId (Long id){

        Account account = new Account();
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setBalance(account.getBalance());
        return balanceDTO;

    }

    public void updateBalance(Long id, Money balance) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            account.get().setBalance(balance);
            accountRepository.save(account.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    @Override
    public void deleteAccountById(Long id) {
        try {

            accountRepository.deleteById(id);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Something went wrong trying to delete the Account...");
        }
    }


    public void updateStatus(Long id, Status status) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            if (account.get() instanceof CreditCard) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit Card do not have a state");
            }
            account.get().setId(id);
            if (account.get() instanceof Savings) {
                ((Savings) account.get()).setStatus(status);
                accountRepository.save(account.get());
                savingsRepository.save((Savings) account.get());
            }
            if (account.get() instanceof Checking) {
                ((Checking) account.get()).setStatus(status);
                accountRepository.save(account.get());
                checkingRepository.save((Checking) account.get());
            }

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    public AccountGetDTO findAccountById(Long id){
     Optional <Account> accountOp = accountRepository.findById(id);
     if(accountOp.isPresent()){
         Account account = accountOp.get();
         AccountGetDTO accountGetDTO = new AccountGetDTO();
         accountGetDTO.setBalance(account.getBalance());
         accountGetDTO.setDateOfCreation(account.getDateOfCreation());
         accountGetDTO.setId(account.getId());
         accountGetDTO.setUser(account.getUser());

         List<TransactionDTO> receivedTransactionsList = new ArrayList<>();
         List<TransactionDTO> sendTransactionsList = new ArrayList<>();
         TransactionDTO transaction = new TransactionDTO();

         for (Transaction transactions : account.getReceivedTransactions()) {
             transaction.setAmount(transactions.getAmount());
             transactions.setId(transactions.getId());
             transactions.setTransactionDate(transactions.getTransactionDate());
             transactions.setDescription(transactions.getDescription());
             transactions.setDestinationAccount(transactions.getDestinationAccount());
             transactions.setOrigenAccount(transactions.getOrigenAccount());
             receivedTransactionsList.add(transaction);
         }

         for (Transaction transactions : account.getSentTransactions()) {
             transaction.setAmount(transactions.getAmount());
             transactions.setId(transactions.getId());
             transactions.setTransactionDate(transactions.getTransactionDate());
             transactions.setDescription(transactions.getDescription());
             transactions.setDestinationAccount(transactions.getDestinationAccount());
             transactions.setOrigenAccount(transactions.getOrigenAccount());
             sendTransactionsList.add(transaction);

         }

         accountGetDTO.setReceivedTransactions(receivedTransactionsList);
         accountGetDTO.setSentTransactions(sendTransactionsList);
         return accountGetDTO;
     }else{
         throw new IllegalArgumentException("This account does not exist");
     }
    }

    public void updateAccount (Long id, double balance){

        Optional <Account> accountOp = accountRepository.findById(id);
        if(accountOp.isPresent()){
            Account account = accountOp.get();
            account.setBalance(new Money(new BigDecimal(balance)));
            accountRepository.save(account);
        }

    }
}

