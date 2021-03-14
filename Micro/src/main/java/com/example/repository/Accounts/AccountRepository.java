package com.example.repository.Accounts;

import com.example.controller.dto.BalanceDTO;
import com.example.controller.dto.BalanceGetDTO;
import com.example.model.Account.Account;
import com.example.model.others.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM account WHERE primary_owner= ?1", nativeQuery = true)
    List<Account> findByUserId(Long id);

    List<Account> findByUser(User user);

    @Query(value = "SELECT SUM(balance_amount) FROM account WHERE primary_owner = ?1", nativeQuery = true)
    BigDecimal findBalanceByUserId (Long id);

    Optional<Account> findById (Long id);

}
