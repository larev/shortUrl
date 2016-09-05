package ru.larev.shorturl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.larev.shorturl.entity.Account;
import ru.larev.shorturl.entity.Url;
import ru.larev.shorturl.repository.URLRepository;

import java.util.List;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private ShortEncoderDecoder encoderDecoder;
    @Autowired
    private URLRepository urlRepository;

    public List<Url> getStatisticFromAccount(Account account) {
        return urlRepository.findByAccount(account);
    }

    @Transactional
    public String createUrl(Account account, String longUrl, int redirectType) {
        Url url = new Url();
        url.setAccount(account);
        url.setUrl(longUrl);
        if (redirectType != 301 && redirectType != 302) redirectType = 301;
        url.setRedirectType(redirectType);
        urlRepository.save(url);
        String shortUrl = encoderDecoder.encode(url.getId());
        url.setShortUrl(shortUrl);
        urlRepository.save(url);
        return shortUrl;
    }

    @Transactional
    public Url getUrlWithIncCounter(String shortUrl) {
        Url byShortUrl = urlRepository.findByShortUrl(shortUrl);
        byShortUrl.setCounter(byShortUrl.getCounter() + 1);
        urlRepository.save(byShortUrl);
        return byShortUrl;
    }
}
