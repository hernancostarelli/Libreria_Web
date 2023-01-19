package com.example.library.service.impl;

import com.example.library.exception.AdminException;
import com.example.library.model.entity.Admin;
import com.example.library.model.enums.EExceptionMessage;
import com.example.library.repository.IAdminRepository;
import com.example.library.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final IAdminRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(String name, String surname, String email, String password, String confirmPassword) throws AdminException {
        validate(name, surname, email, password, confirmPassword);
        Admin admin = new Admin();
        admin.setName(name);
        admin.setSurname(surname);
        admin.setEmail(email);
        String encryptedPassword = passwordEncoder.encode(password);
        admin.setPassword(encryptedPassword);
        repository.save(admin);
    }

    public void validate(String name, String surname, String email, String password, String confirmPassword) throws AdminException {
        if (name == null || name.isEmpty()) {
            throw new AdminException(EExceptionMessage.THE_ADMIN_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (surname == null || surname.isEmpty()) {
            throw new AdminException(EExceptionMessage.THE_ADMIN_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (email == null || email.isEmpty()) {
            throw new AdminException(EExceptionMessage.THE_ADMIN_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (repository.existsByEmail(email)) {
            throw new AdminException(EExceptionMessage.EMAIL_ALREADY_EXISTS.getMessage(email));
        }
        if (password == null || password.isEmpty()) {
            throw new AdminException(EExceptionMessage.THE_ADMIN_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (password.length() < 6) {
            throw new AdminException(EExceptionMessage.THE_ADMIN_PASSWORD_CANNOT_BE_SHORTER_THAN_6_CHARACTERS.toString());
        }
        if (!(password.equals(confirmPassword))) {
            throw new AdminException(EExceptionMessage.THE_ENTERED_PASSWORDS_DO_NOT_MATCH.toString());
        }
    }
}