package com.easyjava.generator.utils;

import cn.hutool.core.util.StrUtil;

public abstract class StrUtils {
    public static String firstLetter2Upper(String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    public static String firstLetter2Lower(String str) {
        if (str == null || str.trim().equals("")) {
            return "";
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    public static String toCamelCase(String field, Boolean upCaseFirstLetter) {
        StringBuffer sb = new StringBuffer();
        String[] fields = field.split("_");
        sb.append(upCaseFirstLetter ? StrUtils.firstLetter2Upper(fields[0]) : fields[0]);
        for (int i = 1, len = fields.length; i < len; i++) {
            sb.append(StrUtils.firstLetter2Upper(fields[i]));
        }
        return sb.toString();
    }
}
