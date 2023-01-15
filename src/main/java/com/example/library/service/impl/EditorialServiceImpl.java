package com.example.library.service.impl;

import com.example.library.exception.EditorialException;
import com.example.library.model.entity.Editorial;
import com.example.library.model.enums.EExceptionMessage;
import com.example.library.repository.IEditorialRepository;
import com.example.library.service.IEditorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditorialServiceImpl implements IEditorialService {

    private final IEditorialRepository repository;

    @Override
    public void save(String name) throws EditorialException {
        validate(name);
        Editorial editorial = new Editorial();
        editorial.setName(name);
        repository.save(editorial);
    }

    @Override
    public void modify(String id, String name) throws EditorialException {
        Optional<Editorial> optionalEditorial = repository.findById(id);
        if (optionalEditorial.isPresent()) {
            Editorial editorial = optionalEditorial.get();
            validate(name);
            editorial.setName(name);
            editorial.setModificationDate(new Date());
            repository.save(editorial);
        }
    }

    @Override
    public void enable(String id) throws EditorialException {
        Optional<Editorial> optionalEditorial = repository.findById(id);
        if (optionalEditorial.isPresent()) {
            Editorial editorial = optionalEditorial.get();
            editorial.setDeleted(false);
            editorial.setModificationDate(new Date());
            repository.save(editorial);
        } else {
            throw new EditorialException(EExceptionMessage.EDITORIAL_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String id) throws EditorialException {
        Optional<Editorial> optionalEditorial = repository.findById(id);
        if (optionalEditorial.isPresent()) {
            Editorial editorial = optionalEditorial.get();
            editorial.setDeleted(true);
            editorial.setModificationDate(new Date());
            repository.save(editorial);
        } else {
            throw new EditorialException(EExceptionMessage.EDITORIAL_NOT_FOUND.toString());
        }
    }

    @Override
    public Editorial getById(String id) throws EditorialException {
        Optional<Editorial> optionalEditorial = repository.findById(id);
        if (optionalEditorial.isPresent()) {
            return optionalEditorial.get();
        } else {
            throw new EditorialException(EExceptionMessage.EDITORIAL_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Editorial> getAll() throws EditorialException {
        List<Editorial> editorialList = repository.findAll();
        if (!(editorialList.isEmpty())) {
            return editorialList;
        } else {
            throw new EditorialException(EExceptionMessage.ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_EDITORIALS.toString());
        }
    }

    @Override
    public List<Editorial> getByValue(String value) throws EditorialException {
        if (value != null) {
            return repository.getByValue("%" + value + "%");
        } else {
            throw new EditorialException(EExceptionMessage.EDITORIAL_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Editorial> getForEnable() throws EditorialException {
        List<Editorial> editorialList = repository.getForEnable();
        if (editorialList != null) {
            return editorialList;
        } else {
            throw new EditorialException(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_EDITORIALS.toString());
        }
    }

    @Override
    public List<Editorial> getForDisable() throws EditorialException {
        List<Editorial> editorialList = repository.getForDisable();
        if (editorialList != null) {
            return editorialList;
        } else {
            throw new EditorialException(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_EDITORIALS.toString());
        }
    }

    public void validate(String name) throws EditorialException {
        if (name == null || name.isEmpty()) {
            throw new EditorialException(EExceptionMessage.THE_EDITORIAL_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
    }
}