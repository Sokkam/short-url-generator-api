package com.sokkam.shorturl.utils;

import com.sokkam.shorturl.constant.UrlPrefix;

/**
 * 短链接api生成器
 */
public class ShortUrlGenerator {

    private static final int sextyTwoHex = 62;

    private static final char[] character = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
            'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    public static String createShortUrl(String originalUrl, long number) {
        return UrlPrefix.SOKKAM_URL + changeSixtyTwoHex(number);
    }

    private static String changeSixtyTwoHex(long number) {
        StringBuilder stringBuilder = new StringBuilder();
        while ((number / sextyTwoHex) != 0) {
            // 取余数
            long remainder = number % sextyTwoHex;
            // 根据余数取字符集里的字符，下标从0开始算
            char c = character[(int) remainder];
            stringBuilder.append(c);
            // 取商数，在下一次循环继续使用
            number = number / sextyTwoHex;
        }
        stringBuilder.append(character[(int) number % sextyTwoHex]);
        return stringBuilder.reverse().toString();
    }

}
