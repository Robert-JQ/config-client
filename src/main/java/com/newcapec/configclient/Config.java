package com.newcapec.configclient;

import java.util.Map;

/**
 * @author jqq
 * @version 1.0
 * @description
 * @date 2019/6/26 16:03
 **/
public class Config {

    private static Map<String, String> localItem = Cache.localItem;

    public static String getStringValue(String key, String defaultValue) {
        String value = localItem.get(key);
        if (value != null) {
            return value;
        }
        return defaultValue;
    }

    public static Integer getIntegerValue(String key, Integer defaultValue) {
        String value = localItem.get(key);
        if (value != null) {
            return Integer.valueOf(value);
        }
        return defaultValue;
    }

    public static Long getLongValue(String key, Long defaultValue) {
        String value = localItem.get(key);
        if (value != null) {
            return Long.valueOf(value);
        }
        return defaultValue;
    }

    public static Short getShortValue(String key, Short defaultValue) {
        String value = localItem.get(key);
        if (value != null) {
            return Short.valueOf(value);
        }
        return defaultValue;
    }

    public static Float getFloatValue(String key, Float defaultValue) {
        String value = localItem.get(key);
        if (value != null) {
            return Float.valueOf(value);
        }
        return defaultValue;
    }

    public static Double getDoubleValue(String key, Double defaultValue) {
        String value = localItem.get(key);
        if (value != null) {
            return Double.valueOf(value);
        }
        return defaultValue;
    }

    public static Byte getByteValue(String key, Byte defaultValue) {
        String value = localItem.get(key);
        if (value != null) {
            return Byte.valueOf(value);
        }
        return defaultValue;
    }

    public static Boolean getBooleanValue(String key, Boolean defaultValue) {
        String value = localItem.get(key);
        if (value != null) {
            return Boolean.valueOf(value);
        }
        return defaultValue;
    }
}
