package com.zero.ioc.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zero.ioc.base.BeanDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public final class JsonUtils {

    private static Gson gson = new Gson();
    private static Type beanDefinitionListType = new TypeToken<List<BeanDefinition>>() {
    }.getType();

    private JsonUtils() {
    }

    public static List<BeanDefinition> jsonToBeanDefinition(InputStream is) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        char[] buf = new char[1024];
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            count = inputStreamReader.read(buf);
            if (count < 0) {
                break;
            }
            sb.append(new String(buf, 0, count));
        }
        return jsonToBeanDefinition(sb.toString());
    }

    public static List<BeanDefinition> jsonToBeanDefinition(String json) {
        return gson.fromJson(json, beanDefinitionListType);
    }
}
