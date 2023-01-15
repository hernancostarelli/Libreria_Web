package com.example.library.service.impl;

import com.example.library.exception.AuthorException;
import com.example.library.model.entity.Author;
import com.example.library.model.enums.EExceptionMessage;
import com.example.library.repository.IAuthorRepository;
import com.example.library.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

    private final IAuthorRepository repository;

    @Override
    public void save(String name, String surname) throws AuthorException {
        validate(name, surname);
        Author author = new Author();
        author.setName(name);
        author.setSurname(surname);
        repository.save(author);
    }

    @Override
    public void modify(String id, String name, String surname) throws AuthorException {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            validate(name, surname);
            author.setName(name);
            author.setSurname(surname);
            author.setModificationDate(new Date());
            repository.save(author);
        }
    }

    @Override
    public void enable(String id) throws AuthorException {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setDeleted(false);
            author.setModificationDate(new Date());
            repository.save(author);
        } else {
            throw new AuthorException(EExceptionMessage.AUTHOR_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String id) throws AuthorException {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();
            author.setDeleted(true);
            author.setModificationDate(new Date());
            repository.save(author);
        } else {
            throw new AuthorException(EExceptionMessage.AUTHOR_NOT_FOUND.toString());
        }
    }

    @Override
    public Author getById(String id) throws AuthorException {
        Optional<Author> optionalAuthor = repository.findById(id);
        if (optionalAuthor.isPresent()) {
            return optionalAuthor.get();
        } else {
            throw new AuthorException(EExceptionMessage.AUTHOR_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Author> getAll() throws AuthorException {
        List<Author> authorList = repository.findAll();
        if (!(authorList.isEmpty())) {
            return authorList;
        } else {
            throw new AuthorException(EExceptionMessage.ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_AUTHORS.toString());
        }
    }

    @Override
    public List<Author> getByValue(String value) throws AuthorException {
        if (value != null) {
            return repository.getByValue("%" + value + "%");
        } else {
            throw new AuthorException(EExceptionMessage.AUTHOR_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Author> getForEnable() throws AuthorException {
        List<Author> authorList = repository.getForEnable();
        if (authorList != null) {
            return authorList;
        } else {
            throw new AuthorException(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_AUTHORS.toString());
        }
    }

    @Override
    public List<Author> getForDisable() throws AuthorException {
        List<Author> authorList = repository.getForDisable();
        if (authorList != null) {
            return authorList;
        } else {
            throw new AuthorException(EExceptionMessage.ERROR_WHEN_DISPLAYING_INACTIVE_AUTHORS.toString());
        }
    }

    public void validate(String name, String surname) throws AuthorException {
        if (name == null || name.isEmpty()) {
            throw new AuthorException(EExceptionMessage.THE_AUTHOR_NAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (surname == null || surname.isEmpty()) {
            throw new AuthorException(EExceptionMessage.THE_AUTHOR_SURNAME_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
    }
}