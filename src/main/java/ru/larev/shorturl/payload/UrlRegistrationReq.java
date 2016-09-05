package ru.larev.shorturl.payload;

import org.hibernate.validator.constraints.URL;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public class UrlRegistrationReq {
    @URL
    private String url;

    private int redirectType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(int redirectType) {
        this.redirectType = redirectType;
    }
}
