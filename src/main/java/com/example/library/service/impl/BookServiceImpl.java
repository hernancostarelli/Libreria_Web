package com.example.library.service.impl;

import com.example.library.exception.AuthorException;
import com.example.library.exception.BookException;
import com.example.library.exception.EditorialException;
import com.example.library.model.entity.Author;
import com.example.library.model.entity.Book;
import com.example.library.model.entity.Editorial;
import com.example.library.model.enums.EExceptionMessage;
import com.example.library.repository.IBookRepository;
import com.example.library.service.IAuthorService;
import com.example.library.service.IBookService;
import com.example.library.service.IEditorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private final IBookRepository repository;
    private final IAuthorService authorService;
    private final IEditorialService editorialService;

    @Override
    public void save(String title, String year, Integer copies, String author, String editorial) throws BookException, AuthorException, EditorialException {
        validate(title, year, copies, author, editorial);
        Book book = new Book();
        book.setTitle(title);
        book.setYear(year);
        book.setCopies(copies);
        book.setAuthor(authorService.getById(author));
        book.setEditorial(editorialService.getById(editorial));
        repository.save(book);
    }

    @Override
    public void modify(String id, String title, String year, Integer copies, String author, String editorial) throws BookException, AuthorException, EditorialException {
        Optional<Book> bookOptional = repository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            validate(title, year, copies, author, editorial);
            book.setTitle(title);
            book.setYear(year);
            book.setCopies(copies);
            book.setAuthor(authorService.getById(author));
            book.setEditorial(editorialService.getById(editorial));
            book.setModificationDate(new Date());
            repository.save(book);
        }
    }

    @Override
    public void enable(String id) throws BookException {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setDeleted(false);
            book.setModificationDate(new Date());
            repository.save(book);
        } else {
            throw new BookException(EExceptionMessage.BOOK_NOT_FOUND.toString());
        }
    }

    @Override
    public void disable(String id) throws BookException {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setDeleted(true);
            book.setModificationDate(new Date());
            repository.save(book);
        } else {
            throw new BookException(EExceptionMessage.BOOK_NOT_FOUND.toString());
        }
    }

    @Override
    public Book getById(String id) throws BookException {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new BookException(EExceptionMessage.BOOK_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Book> getAll() throws BookException {
        List<Book> bookList = repository.findAll();
        if (!(bookList.isEmpty())) {
            return bookList;
        } else {
            throw new BookException(EExceptionMessage.ERROR_WHEN_DISPLAYING_A_LIST_OF_ALL_BOOKS.toString());
        }
    }

    @Override
    public List<Book> getByValue(String value) throws BookException {
        if (value != null) {
            return repository.getByValue("%" + value + "%");
        } else {
            throw new BookException(EExceptionMessage.BOOK_NOT_FOUND.toString());
        }
    }

    @Override
    public List<Book> getForEnable() throws BookException {
        List<Book> bookList = repository.getForEnable();
        if (bookList != null) {
            return bookList;
        } else {
            throw new BookException(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_BOOKS.toString());
        }
    }

    @Override
    public List<Book> getForDisable() throws BookException {
        List<Book> bookList = repository.getForDisable();
        if (bookList != null) {
            return bookList;
        } else {
            throw new BookException(EExceptionMessage.ERROR_WHEN_DISPLAYING_ACTIVE_BOOKS.toString());
        }
    }

    public void validate(String title, String year, Integer copies, String author, String editorial) throws
            BookException, AuthorException, EditorialException {
        if (title == null || title.isEmpty()) {
            throw new BookException(EExceptionMessage.THE_BOOK_TITLE_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        if (year == null || year.isEmpty()) {
            throw new BookException(EExceptionMessage.THE_BOOK_YEAR_CANNOT_BE_EMPTY_OR_BE_NULL.toString());
        }
        int yearToInt = Integer.parseInt(year);
        if (yearToInt > new Date().getYear()) {
            throw new BookException(EExceptionMessage.THE_YEAR_OF_PUBLICATION_CANNOT_BE_OLDER_THAN_THE_CURRENT_DATE.toString());
        }
        if (copies == null || copies < 0) {
            throw new BookException(EExceptionMessage.THE_NUMBER_OF_ITEMS_CANNOT_BE_EMPTY_OR_LESS_THAN_0.toString());
        }
        Author authorIfExist = authorService.getById(author);
        if (authorIfExist == null) {
            throw new AuthorException(EExceptionMessage.AUTHOR_NOT_FOUND.toString());
        }
        Editorial editorialIfExist = editorialService.getById(editorial);
        if (editorialIfExist == null) {
            throw new EditorialException(EExceptionMessage.EDITORIAL_NOT_FOUND.toString());
        }
    }
}