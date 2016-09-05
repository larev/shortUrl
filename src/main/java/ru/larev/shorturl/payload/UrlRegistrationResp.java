package ru.larev.shorturl.payload;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public class UrlRegistrationResp {
    private String shortUrl;

    public UrlRegistrationResp(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
