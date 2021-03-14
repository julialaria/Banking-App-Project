package com.example.repository.Accounts;


import com.example.model.Account.CreditCard;
import com.example.model.Account.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {

    @Query(value = "select * from account a inner Join savings s on s.id = a.id where a.primary_owner = ?1", nativeQuery = true)
    List<Savings> findSavingsByUserId(Long id);
}
