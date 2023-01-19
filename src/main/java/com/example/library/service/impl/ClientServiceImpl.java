package com.example.library.service.impl;

import com.example.library.exception.ClientException;
import com.example.library.model.entity.Client;
import com.example.library.model.enums.EExceptionMessage;
import com.example.library.repository.IClientRepository;
import com.example.library.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService, UserDetailsService {

    private final IClientRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void save(String name, String surname, String document, String telephone, String email, String password, String confirmPassword) throws ClientException {
        validate(name, surname, document, telephone, email);
        validatePassword(password, confirmPassword);
        Client client = new Client();
        client.setName(name);
        client.setSurname(surname);
        client.setDocument(document);
        client.setTelephone(telephone);
        client.setEmail(email);
        String encryptedPassword = passwordEncoder.encode(password);
        client.setPassword(encryptedPassword);
        repository.save(client);
    }

    @Override
    public void modify(String id, String name, String surname, String document, String telephone, String email) throws ClientException {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            validate(name, surname, document, telephone, email);
            client.setName(name);
            client.setSurname(surname);
            client.setDocument(document);
            client.setTelephone(telephone);
            client.setEmail(email);
            client.setModificationDate(new Date());
            repository.save(client);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.toString());
        }
    }

    @Override
    public void modifyPassword(String id, String oldPassword, String password, String confirmPassword) throws ClientException {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            validatePasswordModify(id, oldPassword, password, confirmPassword);
            String encryptedPassword = passwordEncoder.encode(password);
            client.setPassword(encryptedPassword);
            client.setModificationDate(new Date());
            repository.save(client);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.toString());
        }
    }

    @Override
    public void enable(String id) throws ClientException {
        Optional<Client> clientOptional = repository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setDeleted(false);
            client.setModificationDate(new Date());
            repository.save(client);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String id) throws ClientException {
        Optional<Client> clientOptional = repository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setDeleted(true);
            client.setModificationDate(new Date());
            repository.save(client);
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.toString());
        }
    }

    @Override
    public Client getById(String id) throws ClientException {
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Client> getAll() throws ClientException {
        List<Client> clientList = repository.findAll();
        if (!(clientList.isEmpty())) {
            return clientList;
        } else {
            throw new ClientException(EExceptionMessage.ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_CLIENTS.toString());
        }
    }

    @Override
    public List<Client> getByValue(String value) throws ClientException {
        if (value != null) {
            return repository.getByValue("%" + value + "%");
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Client> getForEnable() throws ClientException {
        List<Client> clientList = repository.getForEnable();
        if (clientList != null) {
            return clientList;
        } else {
            throw new ClientException(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_CLIENTS.toString());
        }
    }

    @Override
    public List<Client> getForDisable() throws ClientException {
        List<Client> clientList = repository.getForDisable();
        if (clientList != null) {
            return clientList;
        } else {
            throw new ClientException(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_CLIENTS.toString());
        }
    }


    public void validate(String name, String surname, String document, String telephone, String email) throws ClientException {
        if (name == null || name.isEmpty()) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (surname == null || surname.isEmpty()) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (document == null || document.isEmpty()) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_DOCUMENT_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (repository.existsByDocument(document)) {
            throw new ClientException(EExceptionMessage.DOCUMENT_ALREADY_EXISTS.toString());
        }
        if (document.length() != 8) {
            throw new ClientException(EExceptionMessage.THE_DOCUMENT_MUST_CONTAIN_8_NUMBERS.toString());
        }
        if (telephone == null || telephone.isEmpty()) {
            throw new ClientException(EExceptionMessage.THE_TELEPHONE_NUMBER_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (email == null || email.isEmpty()) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_EMAIL_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (repository.existsByEmail(email)) {
            throw new ClientException(EExceptionMessage.EMAIL_ALREADY_EXISTS.toString());
        }
    }

    public void validatePassword(String password, String confirmPassword) throws ClientException {
        if (password == null || password.isEmpty()) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (password.length() < 6) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_PASSWORD_CANNOT_BE_SHORTER_THAN_6_CHARACTERS.toString());
        }
        if (!(password.equals(confirmPassword))) {
            throw new ClientException(EExceptionMessage.THE_ENTERED_PASSWORDS_DO_NOT_MATCH.toString());
        }
    }

    public void validatePasswordModify(String id, String oldPassword, String password, String confirmPassword) throws ClientException {
        if (oldPassword == null || oldPassword.isEmpty()) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_OLD_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        Optional<Client> optionalClient = repository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean passwordChecker = encoder.matches(oldPassword, client.getPassword());
            if (!(passwordChecker)) {
                throw new ClientException(EExceptionMessage.WRONG_PASSWORD.toString());
            }
        } else {
            throw new ClientException(EExceptionMessage.CLIENT_NOT_FOUND.toString());
        }
        if (password == null || password.isEmpty()) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_PASSWORD_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (password.length() < 6) {
            throw new ClientException(EExceptionMessage.THE_CLIENT_PASSWORD_CANNOT_BE_SHORTER_THAN_6_CHARACTERS.toString());
        }
        if (!(password.equals(confirmPassword))) {
            throw new ClientException(EExceptionMessage.THE_ENTERED_PASSWORDS_DO_NOT_MATCH.toString());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = repository.findUserByEmail(email);
        if (client != null) {
            List<GrantedAuthority> permissions = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + client.getRole());
            permissions.add(grantedAuthority);
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attributes.getRequest().getSession(true);
            session.setAttribute("userSession", client);
            return new User(client.getEmail(), client.getPassword(), permissions);
        } else {
            return null;
        }
    }
}