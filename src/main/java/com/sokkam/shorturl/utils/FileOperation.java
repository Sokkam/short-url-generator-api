package com.sokkam.shorturl.utils;

import com.alibaba.fastjson.JSON;
import com.sokkam.shorturl.domain.Url;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class FileOperation {

    private static final String STORAGE_JSON_FILE_PATH = "short_api_storage.json";

    static void writeJsonFile(List<Url> urlList, String filePath) {
        String jsonContent = JSON.toJSONString(urlList);
        try {
            // 设置成true是追加，false是覆盖
            FileOutputStream fileOutputStream = new FileOutputStream(filePath + STORAGE_JSON_FILE_PATH, false);
            fileOutputStream.write(jsonContent.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static List<Url> readJsonFile(String filePath) {
        try {
            InputStream inputStream = new FileInputStream(filePath + STORAGE_JSON_FILE_PATH);
            String jsonString = IOUtils.toString(inputStream, "utf-8");
            inputStream.close();
            return JSON.parseArray(jsonString, Url.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
