package franc.bankAccounts.controllers;

import franc.bankAccounts.models.Account;
import franc.bankAccounts.services.AccountService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;

import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {
    @Autowired
    AccountService accountService;

    private List<Account> accounts;
    private Account selectedAccount;
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init() {
        logger.info("IndexController init()");
        loadData();
    }

    public void loadData() {
        logger.info("IndexController loadData()");
        this.accounts = accountService.listAccounts()
                .stream()
                .filter(Account::isActive)
                .toList();
        accounts.forEach(account -> logger.info(account.toString()));
        logger.info("Accounts have been listed successfully.");
    }

    public void addAccount() {
        this.selectedAccount = new Account();
    }
}
