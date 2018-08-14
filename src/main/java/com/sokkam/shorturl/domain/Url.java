package com.sokkam.shorturl.domain;

import com.alibaba.fastjson.JSON;

public class Url {

    private long id;

    private String originalUrl;

    private String shortUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
