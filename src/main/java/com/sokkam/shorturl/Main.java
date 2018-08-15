package com.sokkam.shorturl;

import com.sokkam.shorturl.utils.UrlParser;

public class Main {

    public static void main(String[] args) {

        // 原地址
        final String url = "";
        // 输出文件到哪
        final String filePath = "";
        UrlParser parser = new UrlParser();
        String jsonContent = parser.storage(url, filePath);
        System.out.println(jsonContent);

    }

}
