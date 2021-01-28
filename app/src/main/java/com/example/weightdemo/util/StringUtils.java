package com.example.weightdemo.util;

public class StringUtils {
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    public static boolean isBlank(String str) {
        return str == null || str.equals("");
    }
    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }
}
