package com.example.library.exception;

public class LoanException extends Exception {

    private static final long serialVersionUID = 1L;

    public LoanException(String message) {
        super(message);
    }
}