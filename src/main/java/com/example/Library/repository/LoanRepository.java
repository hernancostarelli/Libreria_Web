package com.example.Library.repository;

import com.example.Library.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<String, Loan> {
}