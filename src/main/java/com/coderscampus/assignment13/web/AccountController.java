package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public AccountController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @PostMapping("/users/{userId}/accounts")
    public String postCreateAccount(@PathVariable Long userId) {
        User user = userService.findById(userId);
        accountService.addNewAccountToUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    @GetMapping("/users/{userId}/accounts/{accountId}")
    public String getUpdateAccount(ModelMap model, @PathVariable Long userId, @PathVariable Long accountId) {
        User user = userService.findById(userId);
        Account account = accountService.findByAccountId(accountId);
        model.put("user", user);
        model.put("account", account);
        return "account";
    }

    @PostMapping("/users/{userId}/accounts/{accountId}")
    public String postUpdateAccount(Account account, @PathVariable Long userId) {
        User user = userService.findById(userId);
        Account savedAccount = accountService.saveAccount(account);
        return "redirect:/users/" +  user.getUserId() + "/accounts/" + savedAccount.getAccountId();
    }
}
