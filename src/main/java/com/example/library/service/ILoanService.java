package com.example.library.service;

import com.example.library.exception.BookException;
import com.example.library.exception.ClientException;
import com.example.library.exception.LoanException;
import com.example.library.model.entity.Loan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface ILoanService {

    @Transactional
    void makeLoan(String client, String book) throws LoanException, BookException, ClientException;

    @Transactional
    void stretchReturnDate(String id) throws LoanException;

    @Transactional
    void returnLoan(String id) throws LoanException;

    @Transactional
    void enable(String id) throws LoanException;

    @Transactional
    void disable(String id) throws LoanException;

    @Transactional(readOnly = true)
    Loan getById(String id) throws LoanException;

    @Transactional(readOnly = true)
    List<Loan> getAll() throws LoanException;

    @Transactional(readOnly = true)
    List<Loan> getByValue(String value) throws LoanException;

    @Transactional(readOnly = true)
    List<Loan> getForEnable() throws LoanException;

    @Transactional(readOnly = true)
    List<Loan> getForDisable() throws LoanException;
}