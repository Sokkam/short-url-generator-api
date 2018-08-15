package com.sokkam.shorturl.controller;

import com.sokkam.shorturl.constant.WarnInfo;
import com.sokkam.shorturl.domain.Url;
import com.sokkam.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("shorturl")
public class ShortUrlServiceController {

    @Autowired
    private ShortUrlService shortUrlService;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String jumpIndex() {
        return "index";
    }

    @GetMapping(value = "/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public Url createShortUrl(@RequestParam String url) {
        return shortUrlService.createShortUrl(url);
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void resolveShortUrl(HttpServletResponse response, @PathVariable String code) {
        shortUrlService.redirectShortUrl(response, code);
    }

    @GetMapping(value = "error", produces = MediaType.APPLICATION_JSON_VALUE)
    public String error() {
        return WarnInfo.NOT_FOUND_SHORT_URL;
    }

}
