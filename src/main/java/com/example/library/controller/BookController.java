package com.example.library.controller;

import com.example.library.exception.AuthorException;
import com.example.library.exception.BookException;
import com.example.library.exception.EditorialException;
import com.example.library.model.entity.Author;
import com.example.library.model.entity.Book;
import com.example.library.model.entity.Editorial;
import com.example.library.service.IAuthorService;
import com.example.library.service.IBookService;
import com.example.library.service.IEditorialService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private static final String REDIRECT_BOOK_SEARCH_BOOK = "redirect:/book/search-book";
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final IBookService service;
    private final IEditorialService editorialService;
    private final IAuthorService authorService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/book/dashboard_book.html";
    }

    @GetMapping("/search-book")
    public String search(ModelMap model, @RequestParam(required = false) String value) throws BookException {
        List<Book> bookList;
        if (value == null) {
            value = "";
        }
        bookList = service.getByValue(value);
        model.put("editorialList", bookList);
        return "/book/search_book.html";
    }

    @GetMapping("/save-book")
    public String save() {
        return "/book/save_book.html";
    }

    @PostMapping("/save-book")
    public String save(ModelMap model, @RequestParam String title, @RequestParam String year, @RequestParam Integer copies,
                       @RequestParam String author, @RequestParam String editorial, RedirectAttributes redirectAttributes) {
        try {
            service.save(title, year, copies, author, editorial);
        } catch (Exception exception) {
            logger.info("OCURRIÓ UN ERROR : {}", exception.getMessage());
            model.put("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("title", title);
            redirectAttributes.addFlashAttribute("year", year);
            redirectAttributes.addFlashAttribute("copies", copies);
            redirectAttributes.addFlashAttribute("author", author);
            redirectAttributes.addFlashAttribute("editorial", editorial);
            return "error.html";
        }
        return "redirect:/book/dashboard";
    }

    @GetMapping("/modify-book/{id}")
    public String modify(ModelMap model, @PathVariable String id) throws BookException {
        model.put("book", service.getById(id));
        return "/book/modify_book.html";
    }

    @PostMapping("/modify-book/{id}")
    public String modify(@PathVariable String id, @RequestParam String title, @RequestParam String year, @RequestParam Integer copies,
                         @RequestParam String author, @RequestParam String editorial) throws BookException, AuthorException, EditorialException {
        service.modify(id, title, year, copies, author, editorial);
        return REDIRECT_BOOK_SEARCH_BOOK;
    }

    @GetMapping("/enable-book/{id}")
    public String enable(@PathVariable String id) throws BookException {
        service.enable(id);
        return REDIRECT_BOOK_SEARCH_BOOK;
    }

    @GetMapping("/disable-book/{id}")
    public String disable(@PathVariable String id) throws BookException {
        service.disable(id);
        return REDIRECT_BOOK_SEARCH_BOOK;
    }

    @GetMapping("/detail-book/{id}")
    public String detail(ModelMap model, @PathVariable String id) throws BookException {
        Book book = service.getById(id);
        model.put("book", book);
        Editorial editorial = book.getEditorial();
        model.put("editorial", editorial);
        Author author = book.getAuthor();
        model.put("author", author);
        return "/book/detail_book.html";
    }
}