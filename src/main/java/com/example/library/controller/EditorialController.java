package com.example.library.controller;

import com.example.library.exception.EditorialException;
import com.example.library.model.entity.Author;
import com.example.library.model.entity.Editorial;
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
@RequestMapping("/editorial")
@RequiredArgsConstructor
public class EditorialController {

    private static final String REDIRECT_EDITORIAL_SEARCH_EDITORIAL = "redirect:/editorial/search-editorial";
    private static final Logger logger = LoggerFactory.getLogger(EditorialController.class);
    private final IEditorialService service;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/editorial/dashboard_editorial.html";
    }

    @GetMapping("/search-editorial")
    public String search(ModelMap model, @RequestParam(required = false) String value) throws EditorialException {
        List<Editorial> editorialList;
        if (value == null) {
            value = "";
        }
        editorialList = service.getByValue(value);
        model.put("editorialList", editorialList);
        return "/editorial/search_editorial.html";
    }

    @GetMapping("/save-editorial")
    public String save() {
        return "/editorial/save_editorial.html";
    }

    @PostMapping("/save-editorial")
    public String save(ModelMap model, @RequestParam String name, RedirectAttributes redirectAttributes) {
        try {
            service.save(name);
        } catch (Exception exception) {
            logger.info("OCURRIÓ UN ERROR : {}", exception.getMessage());
            model.put("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("name", name);
            return "error.html";
        }
        return "redirect:/editorial/dashboard";
    }

    @GetMapping("/modify-editorial/{id}")
    public String modify(ModelMap model, @PathVariable String id) throws EditorialException {
        model.put("editorial", service.getById(id));
        return "/editorial/modify_editorial.html";
    }

    @PostMapping("/modify-editorial/{id}")
    public String modify(@PathVariable String id, @RequestParam String name) throws EditorialException {
        service.modify(id, name);
        return REDIRECT_EDITORIAL_SEARCH_EDITORIAL;
    }

    @GetMapping("/enable-editorial/{id}")
    public String enable(@PathVariable String id) throws EditorialException {
        service.enable(id);
        return REDIRECT_EDITORIAL_SEARCH_EDITORIAL;
    }

    @GetMapping("/disable-editorial/{id}")
    public String disable(@PathVariable String id) throws EditorialException {
        service.disable(id);
        return REDIRECT_EDITORIAL_SEARCH_EDITORIAL;
    }

    @GetMapping("/detail-editorial/{id}")
    public String detail(ModelMap model, @PathVariable String id) throws EditorialException {
        Editorial editorial = service.getById(id);
        model.put("editorial", editorial);
        List<Author> authorList = editorial.getAuthorList();
        model.put("authorList", authorList);
        return "/editorial/detail_editorial.html";
    }
}