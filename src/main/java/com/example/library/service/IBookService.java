package com.example.library.service;

import com.example.library.exception.AuthorException;
import com.example.library.exception.BookException;
import com.example.library.exception.EditorialException;
import com.example.library.model.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IBookService {

    @Transactional
    void save(String title, String year, Integer copies, String author, String editorial) throws BookException, AuthorException, EditorialException;

    @Transactional
    void modify(String id, String title, String year, Integer copies, String author, String editorial) throws BookException, AuthorException, EditorialException;

    @Transactional
    void enable(String id) throws BookException;

    @Transactional
    void disable(String id) throws BookException;

    @Transactional(readOnly = true)
    Book getById(String id) throws BookException;

    @Transactional(readOnly = true)
    List<Book> getAll() throws BookException;

    @Transactional(readOnly = true)
    List<Book> getByValue(String value) throws BookException;

    @Transactional(readOnly = true)
    List<Book> getForEnable() throws BookException;

    @Transactional(readOnly = true)
    List<Book> getForDisable() throws BookException;
}