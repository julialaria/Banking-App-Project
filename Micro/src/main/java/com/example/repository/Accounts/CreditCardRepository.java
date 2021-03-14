package com.example.repository.Accounts;

import com.example.model.Account.Checking;
import com.example.model.Account.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    @Query(value = "select * from account a inner Join credit_card c on c.id = a.id where a.primary_owner = ?1", nativeQuery = true)
    List<CreditCard> findCheckingByUserId(Long id);
}
