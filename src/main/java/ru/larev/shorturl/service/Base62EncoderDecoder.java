package ru.larev.shorturl.service;

import org.springframework.stereotype.Component;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
@Component
public class Base62EncoderDecoder implements ShortEncoderDecoder {
    private static String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public String encode(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("b10 must be nonnegative");
        }
        String ret = "";
        while (id > 0) {
            ret = characters.charAt((int) (id % 62)) + ret;
            id /= 62;
        }
        return ret;
    }

    @Override
    public long decode(String string) {
        for (char character : string.toCharArray()) {
            if (!characters.contains(String.valueOf(character))) {
                throw new IllegalArgumentException("Invalid character(s) in string: " + character);
            }
        }
        long ret = 0;
        string = new StringBuffer(string).reverse().toString();
        long count = 1;
        for (char character : string.toCharArray()) {
            ret += characters.indexOf(character) * count;
            count *= 62;
        }
        return ret;

    }
}
