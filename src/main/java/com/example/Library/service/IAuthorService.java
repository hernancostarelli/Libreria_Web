package com.example.Library.service;

import com.example.Library.exception.AuthorException;
import com.example.Library.model.entity.Author;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IAuthorService {

    @Transactional
    void save(String name, String surname) throws AuthorException;

    @Transactional
    void modify(String id, String name, String surname) throws AuthorException;

    @Transactional
    void enable(String id) throws AuthorException;

    @Transactional
    void disable(String id) throws AuthorException;

    @Transactional(readOnly = true)
    Author getById(String id) throws AuthorException;

    @Transactional(readOnly = true)
    List<Author> getAll() throws AuthorException;

    @Transactional(readOnly = true)
    List<Author> getByValue(String value) throws AuthorException;

    @Transactional(readOnly = true)
    List<Author> getForEnable() throws AuthorException;

    @Transactional(readOnly = true)
    List<Author> getForDisable() throws AuthorException;
}