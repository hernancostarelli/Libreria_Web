package com.example.library.controller;

import com.example.library.exception.LoanException;
import com.example.library.model.entity.Book;
import com.example.library.model.entity.Client;
import com.example.library.model.entity.Loan;
import com.example.library.service.ILoanService;
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
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private static final String REDIRECT_LOAN_SEARCH_LOAN = "redirect:/loan/search-loan";
    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);
    private final ILoanService service;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "/loan/dashboard_loan.html";
    }

    @GetMapping("/search-loan")
    public String search(ModelMap model, @RequestParam(required = false) String value) throws LoanException {
        List<Loan> loanList;
        if (value == null) {
            value = "";
        }
        loanList = service.getByValue(value);
        model.put("loanList", loanList);
        return "/loan/search_loan.html";
    }

    @GetMapping("/save-loan")
    public String save() {
        return "/loan/save_loan.html";
    }

    @PostMapping("/make-loan")
    public String makeLoan(ModelMap model, @RequestParam String idClient, @RequestParam String idBook, RedirectAttributes redirectAttributes) {
        try {
            service.makeLoan(idClient, idBook);
        } catch (Exception exception) {
            logger.info("OCURRIÓ UN ERROR : {}", exception.getMessage());
            model.put("error", exception.getMessage());
            redirectAttributes.addFlashAttribute("idClient", idClient);
            redirectAttributes.addFlashAttribute("idBook", idBook);
            return "error.html";
        }
        return "redirect:/loan/dashboard";
    }

    @PostMapping("/stretch-return-date/{id}")
    public String stretchReturnDate(@PathVariable String id) throws LoanException {
        service.stretchReturnDate(id);
        return REDIRECT_LOAN_SEARCH_LOAN;
    }

    @PostMapping("/return-loan/{id}")
    public String returnLoan(ModelMap model, @PathVariable String id) throws LoanException {
        service.returnLoan(id);
        return REDIRECT_LOAN_SEARCH_LOAN;
    }

    @GetMapping("/enable-loan/{id}")
    public String enable(@PathVariable String id) throws LoanException {
        service.enable(id);
        return REDIRECT_LOAN_SEARCH_LOAN;
    }

    @GetMapping("/disable-loan/{id}")
    public String disable(@PathVariable String id) throws LoanException {
        service.disable(id);
        return REDIRECT_LOAN_SEARCH_LOAN;
    }

    @GetMapping("/detail-loan/{id}")
    public String detail(ModelMap model, @PathVariable String id) throws LoanException {
        Loan loan = service.getById(id);
        model.put("loan", loan);
        Client client = loan.getClient();
        model.put("client", client);
        Book book = loan.getBook();
        model.put("book", book);
        return "/loan/detail_loan.html";
    }
}