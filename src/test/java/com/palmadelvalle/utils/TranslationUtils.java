package com.palmadelvalle.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Objects;

import static com.palmadelvalle.utils.Constants.LABEL_MAPPINGS_PATH;

@Slf4j
public class TranslationUtils {

    private static JsonObject labelMappings;

    private static JsonObject readJson() {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            ClassLoader classLoader = TranslationUtils.class.getClassLoader();
            File file = new File(classLoader.getResource(LABEL_MAPPINGS_PATH).getFile());
            reader = new JsonReader(new FileReader(file.getAbsolutePath()));
        }catch (FileNotFoundException ex) {
            log.error("File not found.");
        }
        if (Objects.nonNull(reader)) return gson.fromJson(reader, JsonObject.class);
        return null;
    }

    private static JsonObject getLabelMappins() {
        return readJson();
    }

    public static String getLabelByLang(String labelKey, String lang) {
        if(Objects.isNull(labelMappings)) {
            labelMappings = getLabelMappins();
        }
        return labelMappings.getAsJsonObject(lang).get(labelKey).getAsString();
    }
}
