package com.example.library.service;

import com.example.library.exception.EditorialException;
import com.example.library.model.entity.Editorial;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IEditorialService {

    @Transactional
    void save(String name) throws EditorialException;

    @Transactional
    void modify(String id, String name) throws EditorialException;

    @Transactional
    void enable(String id) throws EditorialException;

    @Transactional
    void disable(String id) throws EditorialException;

    @Transactional(readOnly = true)
    Editorial getById(String id) throws EditorialException;

    @Transactional(readOnly = true)
    List<Editorial> getAll() throws EditorialException;

    @Transactional(readOnly = true)
    List<Editorial> getByValue(String value) throws EditorialException;

    @Transactional(readOnly = true)
    List<Editorial> getForEnable() throws EditorialException;

    @Transactional(readOnly = true)
    List<Editorial> getForDisable() throws EditorialException;
}