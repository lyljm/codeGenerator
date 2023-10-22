package com.easyjava.generator.builder;

import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.sqlHandler.IResultSetHandler;
import com.easyjava.generator.utils.JDBCUtils;
import com.easyjava.generator.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BuildTable {
    public static final String SQL_SHOW_TABLES_STATUS = "show table status";

    public static final String SQL_SHOW_TABLE_FIELDS = "show full fields from %s";

    public static final String SQL_SHOW_TABLE_INDEX = "show index from %s";

    public static List<TableInfo> readTables() {
        List<TableInfo> tableInfoList = new ArrayList<>();
        JDBCUtils.executeQuery(SQL_SHOW_TABLES_STATUS, new IResultSetHandler() {
            @Override
            public void handle(ResultSet resultSet) throws SQLException {

                while (resultSet.next()) {
                    TableInfo tableInfo = readTableInfo(resultSet);
                    tableInfoList.add(tableInfo);
                }
            }
        });
        return tableInfoList;
    }

    public static void readKeyIndexesInfo(TableInfo tableInfo) {
        // 键名到字段信息的映射
        Map<String, List<FieldInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
        // 字段名到字段信息的映射
        Map<String, FieldInfo> name2FieldInfo = new HashMap<>();
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            name2FieldInfo.put(fieldInfo.getFieldName(), fieldInfo);
        }
        JDBCUtils.executeQuery(String.format(SQL_SHOW_TABLE_INDEX, tableInfo.getTableName()), new IResultSetHandler() {
            @Override
            public void handle(ResultSet resultSet) throws SQLException {
                while (resultSet.next()) {
                    String keyName = resultSet.getString("key_name");
                    int nonUnique = resultSet.getInt("non_unique");
                    String columnName = resultSet.getString("column_name");
                    // 不是unique键
                    if (nonUnique == 1) continue;
                    List<FieldInfo> keyFieldList = keyIndexMap.get(keyName);
                    if (null == keyFieldList) {
                        keyFieldList = new ArrayList<>();
                        keyIndexMap.put(keyName, keyFieldList);
                    }
                    keyFieldList.add(name2FieldInfo.get(columnName));
                }
            }
        });
    }


    /**
     * 读取字段信息到tableInfo
     *
     * @param tableInfo
     */
    public static void readFieldsInfo(TableInfo tableInfo) {
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        JDBCUtils.executeQuery(String.format(SQL_SHOW_TABLE_FIELDS, tableInfo.getTableName()), (resultSet) -> {
            while (resultSet.next()) {
                FieldInfo fieldInfo = getFieldInfo(resultSet);
                // 判断表字段是否有Date等类型
                judgeTableFiled(tableInfo, fieldInfo);
                fieldInfoList.add(fieldInfo);
            }
        });
        tableInfo.setFieldList(fieldInfoList);
    }

    /**
     * 表中的boolean值判断
     *
     * @param tableInfo 表信息
     * @param fieldInfo 字段信息
     */
    private static void judgeTableFiled(TableInfo tableInfo, FieldInfo fieldInfo) {
        String javaType = fieldInfo.getJavaType();
        if ("LocalDateTime".equals(javaType)) {
            tableInfo.setHaveDateTime(true);
        }
        if ("Date".equals(javaType)) {
            tableInfo.setHaveDate(true);
        }
        if ("BigDecimal".equals(javaType)) {
            tableInfo.setHaveBigDecimal(true);
        }
    }

    /**
     * 获取表的详细信息
     *
     * @param resultSet 结果集
     * @return 表的详细信息
     * @throws SQLException
     */
    private static TableInfo readTableInfo(ResultSet resultSet) throws SQLException {
        String tableName = resultSet.getString("name");
        String comment = resultSet.getString("Comment");
        String beanName = tableName;
        if (Constants.IGNORE_TABLE_PREFIX) {
            beanName = tableName.substring(beanName.indexOf("_") + 1);
        }
        beanName = StrUtils.toCamelCase(beanName, true);
        // 构建tableINFO
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName(tableName);
        tableInfo.setComment(comment);
        tableInfo.setBeanName(beanName);
        tableInfo.setBeanParamName(beanName + Constants.QUERY_BEAN_SUFFIX);
        readFieldsInfo(tableInfo);
        readKeyIndexesInfo(tableInfo);
        log.info("\n=====================TableName: {}=======================" +
                        "\nComment:{},\tJavaBean:{},\tBeanParamName:{},\tindexInfo:{},\thaveBigDecimal:{},\thaveDate:{},\thaveDateTime:{}" +
                        "\n============================================================"
                , tableInfo.getTableName()
                , tableInfo.getComment()
                , tableInfo.getBeanName()
                , tableInfo.getBeanParamName()
                , tableInfo.getKeyIndexMap()
                , tableInfo.getHaveBigDecimal()
                , tableInfo.getHaveDate()
                , tableInfo.getHaveDateTime());
        return tableInfo;
    }

    /**
     * @param resultSet 结果集
     * @return 字段详细信息
     * @throws SQLException
     */
    private static FieldInfo getFieldInfo(ResultSet resultSet) throws SQLException {
        String field = resultSet.getString("field");
        String type = resultSet.getString("type");
        if (type.indexOf("(") > 0) {
            type = type.substring(0, type.indexOf("("));
        }
        String extra = resultSet.getString("extra");
        String comment = resultSet.getString("Comment");
        FieldInfo fieldInfo = new FieldInfo();
        fieldInfo.setSqlType(type);
        fieldInfo.setFieldName(field);
        fieldInfo.setIsAutoIncrement("".equals(extra));
        fieldInfo.setPropertyName(StrUtils.toCamelCase(field, false));
        fieldInfo.setJavaType(Constants.dbType2JavaType.get(type));
        fieldInfo.setComment(comment);
        log.info("FieldName:{},\tPropertyName:{},\tType:{},\tjavaType{},\tExtra:{},\tComment:{},\t", field, fieldInfo.getPropertyName(), type, fieldInfo.getJavaType(), extra, comment);
        return fieldInfo;
    }

}
