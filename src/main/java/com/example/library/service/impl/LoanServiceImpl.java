package com.example.library.service.impl;

import com.example.library.exception.BookException;
import com.example.library.exception.ClientException;
import com.example.library.exception.LoanException;
import com.example.library.model.entity.Book;
import com.example.library.model.entity.Client;
import com.example.library.model.entity.Loan;
import com.example.library.model.enums.EExceptionMessage;
import com.example.library.repository.ILoanRepository;
import com.example.library.service.IBookService;
import com.example.library.service.IClientService;
import com.example.library.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private final ILoanRepository repository;
    private final IClientService clientService;
    private final IBookService bookService;

    @Override
    public void makeLoan(String idClient, String idBook) throws LoanException, BookException, ClientException {
        validate(idClient, idBook);
        Loan loan = new Loan();
        Client client = clientService.getById(idClient);
        Book book = bookService.getById(idBook);
        loan.setClient(client);
        loan.setBook(book);
        book.setBorrowedCopies(book.getCopies() + 1);
        book.setRemainingCopies(book.getCopies() - 1);
        Date returnDate = new Date();
        returnDate.setDate(returnDate.getDate() + 7);
        loan.setReturnDate(returnDate);
        repository.save(loan);
    }

    @Override
    public void stretchReturnDate(String id) throws LoanException {
        Optional<Loan> optionalLoan = repository.findById(id);
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            if (!(loan.isStretchReturnDate())) {
                Date stretchReturnDate = loan.getReturnDate();
                stretchReturnDate.setDate(stretchReturnDate.getDate() + 7);
                loan.setReturnDate(stretchReturnDate);
                loan.setModificationDate(new Date());
                loan.setStretchReturnDate(true);
                repository.save(loan);
            } else {
                throw new LoanException(EExceptionMessage.THE_LOAN_HAS_BEEN_EXTENDED.toString());
            }
        } else {
            throw new LoanException(EExceptionMessage.LOAN_NOT_FOUND.toString());
        }
    }

    @Override
    public void returnLoan(String id) throws LoanException {
        Optional<Loan> optionalLoan = repository.findById(id);
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            Book book = loan.getBook();
            book.setBorrowedCopies(book.getCopies() - 1);
            book.setRemainingCopies(book.getCopies() + 1);
            loan.setReturnDate(new Date());
            loan.setModificationDate(new Date());
            repository.save(loan);
        } else {
            throw new LoanException(EExceptionMessage.LOAN_NOT_FOUND.toString());
        }
    }

    @Override
    public void enable(String id) throws LoanException {
        Optional<Loan> optionalLoan = repository.findById(id);
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            loan.setDeleted(false);
            loan.setModificationDate(new Date());
            repository.save(loan);
        } else {
            throw new LoanException(EExceptionMessage.LOAN_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String id) throws LoanException {
        Optional<Loan> optionalLoan = repository.findById(id);
        if (optionalLoan.isPresent()) {
            Loan loan = optionalLoan.get();
            loan.setDeleted(true);
            loan.setModificationDate(new Date());
            repository.save(loan);
        } else {
            throw new LoanException(EExceptionMessage.LOAN_NOT_FOUND.toString());
        }
    }

    @Override
    public Loan getById(String id) throws LoanException {
        Optional<Loan> optionalLoan = repository.findById(id);
        if (optionalLoan.isPresent()) {
            return optionalLoan.get();
        } else {
            throw new LoanException(EExceptionMessage.LOAN_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Loan> getAll() throws LoanException {
        List<Loan> loanList = repository.findAll();
        if (!(loanList.isEmpty())) {
            return loanList;
        } else {
            throw new LoanException(EExceptionMessage.ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_LOANS.toString());
        }
    }

    @Override
    public List<Loan> getByValue(String value) throws LoanException {
        if (value != null) {
            return repository.getByValue("%" + value + "%");
        } else {
            throw new LoanException(EExceptionMessage.LOAN_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Loan> getForEnable() throws LoanException {
        List<Loan> loanList = repository.getForEnable();
        if (loanList != null) {
            return loanList;
        } else {
            throw new LoanException(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_LOANS.toString());
        }
    }

    @Override
    public List<Loan> getForDisable() throws LoanException {
        List<Loan> loanList = repository.getForEnable();
        if (loanList != null) {
            return loanList;
        } else {
            throw new LoanException(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_LOANS.toString());
        }
    }

    public void validate(String client, String book) throws LoanException, ClientException, BookException {
        Client clientExist = clientService.getById(client);
        if (clientExist == null) {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.toString());
        }
        Book bookExist = bookService.getById(book);
        if (bookExist == null) {
            throw new BookException(EExceptionMessage.BOOK_NOT_FOUND.toString());
        }
        if (bookExist.getRemainingCopies() == 0) {
            throw new LoanException(EExceptionMessage.THERE_ARE_NO_BOOKS_AVAILABLE_FOR_LOAN.toString());
        }
    }
}