package com.lariss.bankproject.repository;

import com.lariss.bankproject.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {
    //@Query("select m from Movement m where m.account.number=:accountId")
    //List<Movement> findByAccount(Long accountId);
}
