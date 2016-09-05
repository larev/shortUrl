package ru.larev.shorturl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.larev.shorturl.entity.Account;
import ru.larev.shorturl.repository.AccountRepository;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String createAccount(String accountId) {
        Account account = new Account();
        account.setName(accountId);
        String s = KeyGenerators.string().generateKey();
        account.setPassword(passwordEncoder.encode(s));
        accountRepository.save(account);
        return s;
    }

    public boolean accountExist(String accountId) {
        return accountRepository.findOne(accountId) != null;
    }
}
