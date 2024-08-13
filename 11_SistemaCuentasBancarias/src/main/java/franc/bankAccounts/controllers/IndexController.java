package franc.bankAccounts.controllers;

import franc.bankAccounts.models.Account;
import franc.bankAccounts.services.AccountService;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import lombok.Data;

import org.primefaces.PrimeFaces;
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

    public void updateList() {
        // Not the most elegant solution, but it works!
        logger.info("IndexController updateList()");
        this.loadData();
        this.selectedAccount = this.accounts.getLast();
        logger.info("Last added account to DB: " + this.selectedAccount);
    }

    public void saveAccount() {
        logger.info("IndexController saveAccount()");
        logger.info("Account to save: " + this.selectedAccount);
        if (this.selectedAccount.getId() == null) {
            // create account
            this.selectedAccount.setActive(true);
            this.accountService.saveAccount(this.selectedAccount);
            this.updateList();
            logger.info("Account has been created successfully: " + this.selectedAccount);
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage("Account has been created successfully.")
            );
        }
        // hide window
        PrimeFaces.current().executeScript("PF('accountModalWindow').hide()");
        // update table
        PrimeFaces.current().ajax().update("account-form:messages", "account-form:account-table");
    }
}
