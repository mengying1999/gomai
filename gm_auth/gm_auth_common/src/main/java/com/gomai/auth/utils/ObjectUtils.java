package com.gomai.auth.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 从jwt解析得到的数据是Object类型，转换为具体类型可能出现空指针，
 * 这个工具类进行了一些转换
 */
public class ObjectUtils {

    public static String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Integer toInteger(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof Double || obj instanceof Float) {
            return Integer.valueOf(StringUtils.substringBefore(obj.toString(), "."));
        }
        if (obj instanceof Number) {
            return Integer.valueOf(obj.toString());
        }
        if (obj instanceof String) {
            return Integer.valueOf(obj.toString());
        } else {
            return 0;
        }
    }

    public static Integer toInt(Object obj) {
        return toInteger(obj).intValue();
    }
}