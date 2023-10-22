package com.easyjava.generator.Bean;

import com.easyjava.generator.utils.PropertiesUtils;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;
    public static String QUERY_BEAN_SUFFIX;
    // package
    public static String PACKAGE_BASE;
    public static String PACKAGE_PO;
    public static String PACKAGE_QUERY;
    // path
    public static String PATH_JAVA;
    public static String PATH_MAIN;
    public static String PATH_PO;
    public static String PATH_QUERY;
    // comment
    public static String COMMENT_AUTHOR;
    // toJson 忽略表字段
    public static String IGNORE_BEAN_toJSON_IMPORT;
    public static String IGNORE_BEAN_toJSON_EXPRESSION;
    public static String IGNORE_BEAN_toJSON_FIELD;
    // 日期序列化
    public static boolean BEAN_DATE_SERIALIZE_OPEN;
    public static String BEAN_DATE_SERIALIZE_IMPORT;
    public static String BEAN_DATE_SERIALIZE_EXPRESSION;
    public static String BEAN_DATETIME_SERIALIZE_EXPRESSION;
    // 日期反序列化
    public static boolean BEAN_DATE_DESERIALIZE_OPEN;
    public static String BEAN_DATE_DESERIALIZE_IMPORT;
    public static String BEAN_DATE_DESERIALIZE_EXPRESSION;
    public static String BEAN_DATETIME_DESERIALIZE_EXPRESSION;


    public static Map<String, String> dbType2JavaType = new HashMap<>();

    static {
        /**
         * 忽略表前缀
         */
        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix"));
        /**
         *  queryBean的后缀
         */
        QUERY_BEAN_SUFFIX = PropertiesUtils.getString("suffix.bean.param");
        /**
         * 包信息
         */
        PACKAGE_BASE = PropertiesUtils.getString("package.base");
        PACKAGE_QUERY = PACKAGE_BASE + "." + PropertiesUtils.getString("package.param");
        PACKAGE_PO = PACKAGE_BASE + "." + PropertiesUtils.getString("package.po");
        /**
         * 路径信息
         */
        PATH_MAIN = PropertiesUtils.getString("path.main");
        PATH_JAVA = PATH_MAIN + "/java";
        PATH_PO = PATH_JAVA + "/" + PACKAGE_PO.replace(".", "/");
        PATH_QUERY = PATH_JAVA + "/" + PACKAGE_QUERY.replace(".", "/");
        /**
         * comment
         */
        COMMENT_AUTHOR = PropertiesUtils.getString("comment.author");
        /**
         * 转json时需要忽略的属性
         */
        IGNORE_BEAN_toJSON_IMPORT = PropertiesUtils.getString("ignore.bean.toJson.import");
        IGNORE_BEAN_toJSON_EXPRESSION = PropertiesUtils.getString("ignore.bean.toJson.expression");
        IGNORE_BEAN_toJSON_FIELD = PropertiesUtils.getString("ignore.bean.toJson.field");
        /**
         * 日期序列化
         */
        BEAN_DATE_SERIALIZE_OPEN = PropertiesUtils.getBoolean("bean.date.serialize.open");
        BEAN_DATE_SERIALIZE_IMPORT = PropertiesUtils.getString("bean.date.serialize.import");
        BEAN_DATE_SERIALIZE_EXPRESSION = PropertiesUtils.getString("bean.date.serialize.expression");
        BEAN_DATETIME_SERIALIZE_EXPRESSION = PropertiesUtils.getString("bean.datetime.serialize.expression");
        /**
         * 日期反序列化
         */
        BEAN_DATE_DESERIALIZE_OPEN = PropertiesUtils.getBoolean("bean.date.deserialize.open");
        BEAN_DATE_DESERIALIZE_IMPORT = PropertiesUtils.getString("bean.date.deserialize.import");
        BEAN_DATE_DESERIALIZE_EXPRESSION = PropertiesUtils.getString("bean.date.deserialize.expression");
        BEAN_DATETIME_DESERIALIZE_EXPRESSION = PropertiesUtils.getString("bean.datetime.deserialize.expression");

        /**
         * slq类型到java类型的转换
         */
        dbType2JavaType.put("datetime", "LocalDateTime");
        dbType2JavaType.put("timestamp", "LocalDateTime");
        dbType2JavaType.put("date", "Date");
        dbType2JavaType.put("decimal", "BigDecimal");
        dbType2JavaType.put("double", "Double");
        dbType2JavaType.put("float ", "Float");
        dbType2JavaType.put("char", "String");
        dbType2JavaType.put("varchar", "String");
        dbType2JavaType.put("text", "String");
        dbType2JavaType.put("mediumtext", "String");
        dbType2JavaType.put("longtext", "String");
        dbType2JavaType.put("int", "Integer");
        dbType2JavaType.put("tinyint", "Integer");
        dbType2JavaType.put("bigint", "Long");
    }
}
