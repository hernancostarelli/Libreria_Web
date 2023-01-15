package com.example.library.controller;

import com.example.library.exception.AuthorException;
import com.example.library.model.entity.Author;
import com.example.library.model.entity.Book;
import com.example.library.service.IAuthorService;
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
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private static final String REDIRECT_AUTHOR_SEARCH_AUTHOR = "redirect:/author/search-author";
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private final IAuthorService service;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/author/dashboard_author.html";
    }

    @GetMapping("/search-author")
    public String search(ModelMap model, @RequestParam(required = false) String value) throws AuthorException {
        List<Author> authorList;
        if (value == null) {
            value = "";
        }
        authorList = service.getByValue(value);
        model.put("authorList", authorList);
        return "/author/search_author.html";
    }

    @GetMapping("/save-author")
    public String save() {
        return "/author/save_author.html";
    }

    @PostMapping("/save-author")
    public String save(ModelMap model, @RequestParam String name, @RequestParam String surname, RedirectAttributes redirectAttributes) {
        try {
            service.save(name, surname);
        } catch (Exception exception) {
            logger.info("OCURRIÓ UN ERROR : {}", exception.getMessage());
            model.put("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("surname", surname);
            return "error.html";
        }
        return "redirect:/author/dashboard";
    }

    @GetMapping("/modify-author/{id}")
    public String modify(ModelMap model, @PathVariable String id) throws AuthorException {
        model.put("author", service.getById(id));
        return "/author/modify_author.html";
    }

    @PostMapping("/modify-author/{id}")
    public String modify(@PathVariable String id, @RequestParam String name, @RequestParam String surname) throws AuthorException {
        service.modify(id, name, surname);
        return REDIRECT_AUTHOR_SEARCH_AUTHOR;
    }

    @GetMapping("/enable-author/{id}")
    public String enable(@PathVariable String id) throws AuthorException {
        service.enable(id);
        return REDIRECT_AUTHOR_SEARCH_AUTHOR;
    }

    @GetMapping("/disable-author/{id}")
    public String disable(@PathVariable String id) throws AuthorException {
        service.disable(id);
        return REDIRECT_AUTHOR_SEARCH_AUTHOR;
    }

    @GetMapping("/detail-author/{id}")
    public String detail(ModelMap model, @PathVariable String id) throws AuthorException {
        Author author = service.getById(id);
        model.put("author", author);
        List<Book> bookList = author.getBookList();
        model.put("bookList", bookList);
        return "/author/detail_author.html";
    }
}