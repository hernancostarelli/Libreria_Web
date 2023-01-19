package com.example.library.service;

import com.example.library.exception.ClientException;
import com.example.library.model.entity.Client;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IClientService {

    @Transactional
    void save(String name, String surname, String document, String telephone,  String email, String password, String confirmPassword) throws ClientException;

    @Transactional
    void modify(String id, String name, String surname, String document, String telephone,  String email) throws ClientException;

    @Transactional
    void modifyPassword(String id, String oldPassword, String password, String confirmPassword) throws ClientException;

    @Transactional
    void enable(String id) throws ClientException;

    @Transactional
    void disable(String id) throws ClientException;

    @Transactional(readOnly = true)
    Client getById(String id) throws ClientException;

    @Transactional(readOnly = true)
    List<Client> getAll() throws ClientException;

    @Transactional(readOnly = true)
    List<Client> getByValue(String value) throws ClientException;

    @Transactional(readOnly = true)
    List<Client> getForEnable() throws ClientException;

    @Transactional(readOnly = true)
    List<Client> getForDisable() throws ClientException;
}