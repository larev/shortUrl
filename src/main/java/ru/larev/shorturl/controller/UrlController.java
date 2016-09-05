package ru.larev.shorturl.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.larev.shorturl.entity.Account;
import ru.larev.shorturl.entity.Url;
import ru.larev.shorturl.payload.UrlRegistrationReq;
import ru.larev.shorturl.payload.UrlRegistrationResp;
import ru.larev.shorturl.service.UrlService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UrlRegistrationResp urlRegistration(HttpServletRequest request, Authentication authentication, @Valid UrlRegistrationReq url) {
        Account account = (Account) authentication.getPrincipal();
        String shortUrl = urlService.createUrl(account, url.getUrl(), url.getRedirectType());
        return new UrlRegistrationResp(shortUrl);
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public Map<String, Long> getStatistic(Authentication authentication) {
        Account account = (Account) authentication.getPrincipal();
        List<Url> statisticFromAccount = urlService.getStatisticFromAccount(account);
        return statisticFromAccount.stream().collect(Collectors.groupingBy(Url::getUrl, Collectors.summingLong(Url::getCounter)));
    }

    @RequestMapping(value = "/{shortUrl}", method = RequestMethod.GET)
    public void redirect(@PathVariable String shortUrl, HttpServletResponse httpServletResponse) {
        Url longUrlWithInc = urlService.getUrlWithIncCounter(shortUrl);
        httpServletResponse.setHeader(HttpHeaders.LOCATION, longUrlWithInc.getUrl());
        httpServletResponse.setStatus(longUrlWithInc.getRedirectType());
    }

}
