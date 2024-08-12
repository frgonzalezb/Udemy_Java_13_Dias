package franc.bankAccounts.services;

import franc.bankAccounts.models.Account;

import java.util.List;

public interface IAccountService {
    public List<Account> listAccounts();

    public Account getAccount(Long id);

    public Account saveAccount(Account account);

    public void deleteAccount(Long id);
}
