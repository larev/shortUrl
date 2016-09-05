package ru.larev.shorturl.service;

import ru.larev.shorturl.entity.Account;
import ru.larev.shorturl.entity.Url;

import java.util.List;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public interface UrlService {
    String createUrl(Account account, String longUrl, int redirectType);

    Url getUrlWithIncCounter(String shortUrl);

    List<Url> getStatisticFromAccount(Account account);
}
