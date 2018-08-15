package com.sokkam.shorturl.controller;

import com.sokkam.shorturl.domain.Url;
import com.sokkam.shorturl.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/shortUrl")
public class ShortUrlServiceController {

    @Autowired
    private ShortUrlService shortUrlService;

    @RequestMapping("/generate")
    public Url createShortUrl(@RequestParam String url) {
        return shortUrlService.createShortUrl(url);
    }

    @RequestMapping("/{code}")
    public void resolveShortUrl(HttpServletResponse response, @PathVariable String code) {
        shortUrlService.redirectShortUrl(response, code);
    }

}
