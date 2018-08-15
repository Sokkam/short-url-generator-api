package com.sokkam.shorturl.domain;

import com.alibaba.fastjson.JSON;

public class Url {

    private long id;

    private String originalUrl;

    private String shortUrlCode;

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

    public String getShortUrlCode() {
        return shortUrlCode;
    }

    public void setShortUrlCode(String shortUrlCode) {
        this.shortUrlCode = shortUrlCode;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
