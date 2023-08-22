package com.palmadelvalle.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

import static com.palmadelvalle.utils.Constants.LABEL_MAPPINGS_PATH;

@Slf4j
public class TranslationUtils {

    private static JsonObject labelMappings;

    /**
     * This method read and parse the JSON stored in file label_mappings.json.
     * @return JSONObject with the JSON declared in label_mappings.json.
     */
    private static JsonObject readMappingsFromJsonFile() {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            File file = new File(TranslationUtils.class.getClassLoader().getResource(LABEL_MAPPINGS_PATH).getFile());
            reader = new JsonReader(new FileReader(file.getAbsolutePath()));
        }catch (FileNotFoundException | NullPointerException ex) {
            log.error("File {} not found.", LABEL_MAPPINGS_PATH);
        }
        if (Objects.nonNull(reader)) return gson.fromJson(reader, JsonObject.class);
        return null;
    }

    /**
     * This method lookup in label_mappings.json the key of the label and the value in the language that we want.
     * This keys and his values have been obtained through the JS code of the web imedical.asiainsurance.hk
     * @param labelKey Key of the label. This keys and his values are defined in label_mappings.json
     * @param lang Language in which we want to obtain the values.
     * @return String Value of the label present in the screen in the language that we want.
     */
    public static String getLabelByLang(String labelKey, String lang) {
        if(Objects.isNull(labelMappings)) {
            labelMappings = readMappingsFromJsonFile();
        }
        return labelMappings.getAsJsonObject(lang).get(labelKey).getAsString();
    }
}
