package com.example.Library.repository;

import com.example.Library.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanRepository extends JpaRepository<Loan, String> {
}