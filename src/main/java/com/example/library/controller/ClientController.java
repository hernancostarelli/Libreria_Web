package com.example.library.controller;

import com.example.library.exception.ClientException;
import com.example.library.model.entity.Client;
import com.example.library.model.entity.Loan;
import com.example.library.service.IClientService;
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
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private static final String REDIRECT_CLIENT_SEARCH_CLIENT = "redirect:/client/search-client";
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final IClientService service;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/client/index-client.html";
    }

    @GetMapping("/search-client")
    public String search(ModelMap model, @RequestParam(required = false) String value) throws ClientException {
        List<Client> clientList;
        if (value == null) {
            value = "";
        }
        clientList = service.getByValue(value);
        model.put("authorList", clientList);
        return "/client/search_client.html";
    }

    @GetMapping("/save-client")
    public String save() {
        return "/client/save_client.html";
    }

    @PostMapping("/save-client")
    public String save(ModelMap model, @RequestParam String name, @RequestParam String surname, @RequestParam String document,
                       @RequestParam String telephone, @RequestParam String email, @RequestParam String password,
                       @RequestParam String confirmPassword, RedirectAttributes redirectAttributes) {
        try {
            service.save(name, surname, document, telephone, email, password, confirmPassword);
        } catch (Exception exception) {
            logger.info("OCURRIÓ UN ERROR : {}", exception.getMessage());
            model.put("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("surname", surname);
            redirectAttributes.addFlashAttribute("document", document);
            redirectAttributes.addFlashAttribute("telephone", telephone);
            redirectAttributes.addFlashAttribute("email", email);
            return "error.html";
        }
        return "redirect:/client/dashboard";
    }

    @GetMapping("/modify-client/{id}")
    public String modify(ModelMap model, @PathVariable String id) throws ClientException {
        model.put("client", service.getById(id));
        return "/client/modify_client.html";
    }

    @PostMapping("/modify-client/{id}")
    public String modify(@PathVariable String id, @RequestParam String name, @RequestParam String surname, @RequestParam String document,
                         @RequestParam String telephone, @RequestParam String email) throws ClientException {
        service.modify(id, name, surname, document, telephone, email);
        return REDIRECT_CLIENT_SEARCH_CLIENT;
    }

    @GetMapping("/modify-password-client/{id}")
    public String modifyPassword(ModelMap model, @PathVariable String id) throws ClientException {
        model.put("client", service.getById(id));
        return "/client/modify_password_client.html";
    }

    @PostMapping("/modify-password-client/{id}")
    public String modifyPassword(@PathVariable String id, @RequestParam String oldPassword, @RequestParam String password,
                                 @RequestParam String confirmPassword) throws ClientException {
        service.modifyPassword(id, oldPassword, password, confirmPassword);
        return REDIRECT_CLIENT_SEARCH_CLIENT;
    }

    @GetMapping("/enable-client/{id}")
    public String enable(@PathVariable String id) throws ClientException {
        service.enable(id);
        return REDIRECT_CLIENT_SEARCH_CLIENT;
    }

    @GetMapping("/disable-client/{id}")
    public String disable(@PathVariable String id) throws ClientException {
        service.disable(id);
        return REDIRECT_CLIENT_SEARCH_CLIENT;
    }

    @GetMapping("/detail-client/{id}")
    public String detail(ModelMap model, @PathVariable String id) throws ClientException {
        Client client = service.getById(id);
        model.put("client", client);
        List<Loan> loanList = client.getLoanList();
        model.put("loanList", loanList);
        return "/client/detail_client.html";
    }
}