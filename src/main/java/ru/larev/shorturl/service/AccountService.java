package ru.larev.shorturl.service;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public interface AccountService {
    String createAccount(String accountId);

    boolean accountExist(String accountId);
}
