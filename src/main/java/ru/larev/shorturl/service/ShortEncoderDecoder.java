package ru.larev.shorturl.service;

/**
 * @author Larev Pavel
 * @author http://telegram.me/larev
 */
public interface ShortEncoderDecoder {
    String encode(long string);

    long decode(String string);
}
