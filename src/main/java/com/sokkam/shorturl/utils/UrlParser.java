package com.sokkam.shorturl.utils;

import com.sokkam.shorturl.constant.UrlPrefix;
import com.sokkam.shorturl.constant.WarnInfo;
import com.sokkam.shorturl.domain.Url;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UrlParser {

    private static final long STARTING_NUMBER = 20000000;

    private static final int INCREASE_RATE = 1;

    public String getCorrespondUrl(String code) {
        return optionalVaild().stream().filter(u -> u.getShortUrlCode().equals(code))
                .findFirst()
                .map(Url::getOriginalUrl)
                .orElse(WarnInfo.NOT_FOUND_SHORT_URL);
    }

    public Url storage(String originalUrl) {
        List<Url> urlList = optionalVaild();
        if (urlList.size() == 0) {
            return updateListAndWrite(urlList, originalUrl, urlList.size());
        } else {
            return findUrlByOriginalUrl(urlList, originalUrl);
        }
    }

    private List<Url> optionalVaild() {
        return Optional.ofNullable(FileOperation.readJsonFile()).orElse(new ArrayList<>());
    }

    private Url findUrlByOriginalUrl(List<Url> urlList, String originalUrl) {
        for (Url url : urlList) {
            if (Objects.equals(originalUrl, url.getOriginalUrl())) {
                setShortAllUrl(url);
                return url;
            }
        }
        return updateListAndWrite(urlList, originalUrl, urlList.size());
    }

    private Url updateListAndWrite(List<Url> urlList, String originalUrl, int listLength) {
        Url url = createUrl(originalUrl, listLength);
        urlList.add(url);
        FileOperation.writeJsonFile(urlList);
        setShortAllUrl(url);
        return url;
    }

    private void setShortAllUrl(Url url) {
        url.setShortUrlCode(UrlPrefix.LOCAL_URL_PREFIX + url.getShortUrlCode());
    }

    private Url createUrl(String originalUrl, long listLength) {
        Url url = new Url();
        url.setId(listLength + INCREASE_RATE);
        url.setOriginalUrl(originalUrl);
        url.setShortUrlCode(ShortUrlGenerator.createShortUrl(listLength + STARTING_NUMBER));
        return url;
    }

}
