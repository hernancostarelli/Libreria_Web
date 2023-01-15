package com.example.library.repository;

import com.example.library.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanRepository extends JpaRepository<Loan, String> {
}