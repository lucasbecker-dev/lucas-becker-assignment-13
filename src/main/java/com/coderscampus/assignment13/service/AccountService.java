package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepo;
    private final UserService userService;

    @Autowired
    public AccountService(AccountRepository accountRepo, UserService userService) {
        this.accountRepo = accountRepo;
        this.userService = userService;
    }

    public Account findByAccountId(Long accountId) {
        return accountRepo.findByAccountId(accountId);
    }

    public Account saveAccount(Account account) {
        return accountRepo.save(account);
    }

    public Account addNewAccountToUser(User user) {
        Account newAccount = new Account();
        int newAccountNumber = user.getAccounts().size();
        newAccount.setAccountName("Account #" + newAccountNumber);
        newAccount.getUsers().add(user);
        user.getAccounts().add(newAccount);
        userService.saveUser(user);
        return accountRepo.save(newAccount);
    }
}
