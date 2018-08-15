package com.sokkam.shorturl.service;

import com.sokkam.shorturl.domain.Url;

import javax.servlet.http.HttpServletResponse;

public interface ShortUrlService {

    Url createShortUrl(String url);

    void redirectShortUrl(HttpServletResponse response, String code);

}
