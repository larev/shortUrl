package ru.larev.shorturl.repository;

import org.springframework.data.repository.CrudRepository;
import ru.larev.shorturl.entity.Account;
import ru.larev.shorturl.entity.Url;

import java.util.List;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public interface URLRepository extends CrudRepository<Url, Long> {
    Url findByShortUrl(String shortUrl);

    List<Url> findByAccount(Account account);
}
