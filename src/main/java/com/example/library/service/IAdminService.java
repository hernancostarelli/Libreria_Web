package com.example.library.service;

import com.example.library.exception.AdminException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface IAdminService {

    @Transactional
    void register(String name, String surname, String email, String password, String confirmPassword) throws AdminException;
}