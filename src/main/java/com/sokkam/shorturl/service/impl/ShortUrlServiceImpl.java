package com.sokkam.shorturl.service.impl;

import com.sokkam.shorturl.constant.WarnInfo;
import com.sokkam.shorturl.domain.Url;
import com.sokkam.shorturl.service.ShortUrlService;
import com.sokkam.shorturl.utils.UrlParser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    private UrlParser parser;

    private static final String ERROR_INFO_PAGE_URL = "http://localhost:8090/shorturl/error";

    @Override
    public Url createShortUrl(String url) {
        return parser.storage(url);
    }

    @Override
    public void redirectShortUrl(HttpServletResponse response, String code) {
        String url = parser.getCorrespondUrl(code);
        redirect(response, url);
    }

    private void redirect(HttpServletResponse response, String url) {
        try {
            if (StringUtils.equals(url, WarnInfo.NOT_FOUND_SHORT_URL)) {
                response.sendRedirect(ERROR_INFO_PAGE_URL);
            } else {
                response.sendRedirect(url);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
