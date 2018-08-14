package com.sokkam.shorturl.utils;

import com.sokkam.shorturl.domain.Url;

import java.util.ArrayList;
import java.util.List;

public class UrlParser {

    private static final long STARTING_NUMBER = 20000000;

    public void storage(String originalUrl) {
        List<Url> urlList = FileOperation.readJsonFile();
        if (urlList == null) {
            urlList = new ArrayList<>();
            urlList.add(createUrl(originalUrl));
        } else {
            if (urlList.stream().noneMatch(url -> url.getOriginalUrl().equals(originalUrl))) {
                urlList.add(createUrl(originalUrl));
            } else {
                System.out.println("包含" + originalUrl + "的短链接");
            }
        }
    }

    private Url createUrl(String originalUrl) {
        Url url = new Url();
        url.setId(STARTING_NUMBER);
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(ShortUrlGenerator.createShortUrl(originalUrl, STARTING_NUMBER));
        return url;
    }

}
