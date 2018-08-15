package com.sokkam.shorturl.utils;

import com.sokkam.shorturl.domain.Url;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UrlParser {

    private static final long STARTING_NUMBER = 20000000;

    private static final int INCREASE_RATE = 1;

    public String storage(String originalUrl, String filePath) {
        List<Url> urlList = FileOperation.readJsonFile(filePath);
        if (urlList == null) {
            urlList = new ArrayList<>();
            return updateListAndWrite(urlList, originalUrl, filePath, 0);
        }
        for (Url url : urlList) {
            if (Objects.equals(originalUrl, url.getOriginalUrl())) {
                return url.toString();
            }
        }
        return updateListAndWrite(urlList, originalUrl, filePath, urlList.size());
    }

    private String updateListAndWrite(List<Url> urlList, String originalUrl, String filePath, int listLength) {
        Url url = createUrl(originalUrl, listLength);
        urlList.add(url);
        FileOperation.writeJsonFile(urlList, filePath);
        return url.toString();
    }

    private Url createUrl(String originalUrl, long listLength) {
        Url url = new Url();
        url.setId(listLength + INCREASE_RATE);
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(ShortUrlGenerator.createShortUrl(listLength + STARTING_NUMBER));
        return url;
    }

}
