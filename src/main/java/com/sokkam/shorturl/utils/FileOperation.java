package com.sokkam.shorturl.utils;

import com.alibaba.fastjson.JSON;
import com.sokkam.shorturl.domain.Url;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

public class FileOperation {

    private static final String STORAGE_JSON_FILE_NAME = "short_api_storage.json";

    static void writeJsonFile(List<Url> urlList) {
        String jsonContent = JSON.toJSONString(urlList);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(parserPath(), true);
            fileOutputStream.write(jsonContent.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static List<Url> readJsonFile() {
        try {
            InputStream inputStream = new FileInputStream(parserPath());
            String jsonString = IOUtils.toString(inputStream, "utf-8");
            inputStream.close();
            return JSON.parseArray(jsonString, Url.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String parserPath() {
        return FileOperation.class.getClassLoader().getResource(STORAGE_JSON_FILE_NAME).getPath();
    }

}
