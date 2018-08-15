package com.sokkam.shorturl.utils;

import com.sokkam.shorturl.constant.WarnInfo;
import com.sokkam.shorturl.domain.Url;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UrlParser {

    private static final long STARTING_NUMBER = 20000000;

    private static final int INCREASE_RATE = 1;

    public String getCorrespondUrl(String code) {
        List<Url> urlList = FileOperation.readJsonFile("");
        Url url = getMatchUrl(urlList, code);
        return url != null ? url.getOriginalUrl() : WarnInfo.NOT_FOUND_SHORT_URL;
    }

    private Url getMatchUrl(List<Url> urlList, String code) {
        return urlList.stream()
                .filter(u -> u.getOriginalUrl().equals(code))
                .findFirst()
                .orElse(null);
    }

    public Url storage(String originalUrl, String filePath) {
        List<Url> urlList = FileOperation.readJsonFile(filePath);
        if (urlList == null) {
            urlList = new ArrayList<>();
            return updateListAndWrite(urlList, originalUrl, filePath, 0);
        }
        for (Url url : urlList) {
            if (Objects.equals(originalUrl, url.getOriginalUrl())) {
                return url;
            }
        }
        return updateListAndWrite(urlList, originalUrl, filePath, urlList.size());
    }

    private Url updateListAndWrite(List<Url> urlList, String originalUrl, String filePath, int listLength) {
        Url url = createUrl(originalUrl, listLength);
        urlList.add(url);
        FileOperation.writeJsonFile(urlList, filePath);
        return url;
    }

    private Url createUrl(String originalUrl, long listLength) {
        Url url = new Url();
        url.setId(listLength + INCREASE_RATE);
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(ShortUrlGenerator.createShortUrl(listLength + STARTING_NUMBER));
        return url;
    }

}
