package com.easyjava.generator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class PropertiesUtils {
    private static Properties properties = new Properties();
    private static Map<String, String> PROPER_MAP = new ConcurrentHashMap<String, String>();

    static {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(resourceAsStream);
            Iterator<Object> iterator = properties.keySet().iterator();
            while (iterator.hasNext()) {
                String next = (String) iterator.next();
                PROPER_MAP.put(next, properties.getProperty(next));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String getString(String key) {
        return PROPER_MAP.getOrDefault(key, "");
    }

    public static boolean getBoolean(String key) {
        String res = PROPER_MAP.getOrDefault(key, "");
        return Boolean.valueOf(res);
    }

}
