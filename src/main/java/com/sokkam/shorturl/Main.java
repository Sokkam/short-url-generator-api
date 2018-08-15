package com.sokkam.shorturl;

import com.sokkam.shorturl.utils.UrlParser;

public class Main {

    public static void main(String[] args) {

        // 原地址
        final String url = "http://www.skm.com/wfawfa98wf7y978y91";
        // 输出文件到哪
        final String filePath = "G:/";
        UrlParser parser = new UrlParser();
        String jsonContent = parser.storage(url, filePath);
        System.out.println(jsonContent);

    }

}
