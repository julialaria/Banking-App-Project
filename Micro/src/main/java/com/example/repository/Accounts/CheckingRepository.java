package com.example.repository.Accounts;

import com.example.controller.dto.BalanceDTO;
import com.example.controller.dto.CheckingGetDTO;
import com.example.model.Account.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {


    @Query(value = "select * from account a inner Join checking c on c.id = a.id where a.primary_owner = ?1", nativeQuery = true)
    List<Checking> findCheckingByUserId(Long id);


}
