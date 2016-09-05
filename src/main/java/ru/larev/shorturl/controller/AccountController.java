package ru.larev.shorturl.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.larev.shorturl.exceptions.AccountAllreadyExist;
import ru.larev.shorturl.payload.AccountRegistration;
import ru.larev.shorturl.service.AccountService;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
@RestController
@Validated
public class AccountController {

    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public AccountRegistration accountRegistration(@RequestParam @NotEmpty String accountId) throws AccountAllreadyExist {
        if (!accountService.accountExist(accountId)) {
            String password = accountService.createAccount(accountId);
            return new AccountRegistration(true, "The account is created", password);
        }
        throw new AccountAllreadyExist();
    }

    @ExceptionHandler(AccountAllreadyExist.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AccountRegistration accountExist() {
        return new AccountRegistration(false, "The account is allready exist");
    }
}
