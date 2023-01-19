package com.example.library.repository;

import com.example.library.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILoanRepository extends JpaRepository<Loan, String> {

    @Query("SELECT l FROM Loan l WHERE l.client.name LIKE :value OR l.book.title LIKE :value ORDER BY l.loanDate ASC")
    List<Loan> getByValue(@Param("value")String value);

    @Query("SELECT l FROM Loan l WHERE l.deleted = false ORDER BY l.loanDate ASC")
    List<Loan> getForEnable();

    @Query("SELECT l FROM Loan l WHERE l.deleted = true ORDER BY l.loanDate ASC")
    List<Loan> getForDisable();
}