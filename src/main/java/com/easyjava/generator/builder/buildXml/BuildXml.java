package com.easyjava.generator.builder.buildXml;

import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.BaseBuild;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class BuildXml extends BaseBuild {

    private static final String BASE_QUERY_CONDITION = "Base_Query_Condition";

    private static final String QUERY_CONDITION = "Query_Condition";


    /**
     * 继承该类必须赋值文件路径和文件后缀
     */
    public BuildXml() {
        super(Constants.PATH_XML, Constants.MAPPER_SUFFIX + SUFFIX_XML);
    }

    @Override
    public void construct(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
        bw.write("<mapper namespace=\"" + Constants.PACKAGE_MAPPER + "." + tableInfo.getBeanName() + Constants.MAPPER_SUFFIX + "\">");
        bw.newLine();
        bw.newLine();
        // 构造通用查询映射
        constructCommonResultMap(tableInfo, bw);
        bw.newLine();
        // 构造通用查询列
        constructCommonSelect(tableInfo, bw);
        bw.newLine();
        // 构造通用查询条件
        constructCommonWhereCondition(tableInfo, bw);

        bw.newLine();
        bw.write("</mapper>");
    }

    /**
     * 基础查询条件
     *
     * @param tableInfo
     * @param bw
     */
    private void constructCommonWhereCondition(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        bw.write("\t<!--基础查询条件-->");
        bw.newLine();
        bw.write("\t<sql id=\"" + BASE_QUERY_CONDITION + "\">");
        bw.newLine();
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            bw.write("\t\t<if test=\"" + fieldInfo.getPropertyName() + " != null");
            if ("String".equals(fieldInfo.getJavaType())) {
                bw.write(" and " + fieldInfo.getPropertyName() + " != ''");
            }
            bw.write(" \">");
            bw.newLine();
            bw.write("\t\t\tand " + fieldInfo.getPropertyName() + "=#{" + fieldInfo.getPropertyName() + "}");
            bw.newLine();
            bw.write("\t\t</if>");
            bw.newLine();
        }
        bw.write("\t</sql>");
        bw.newLine();
    }

    /**
     * select结果列
     *
     * @param tableInfo
     * @param bw
     * @throws IOException
     */
    private static void constructCommonSelect(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        bw.write("\t<!--通用查询结果列-->");
        bw.newLine();
        bw.write("\t<sql id = \"Base_Column_List\" >");
        bw.newLine();
        StringBuffer stringBuffer = new StringBuffer();
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            stringBuffer.append(fieldInfo.getFieldName() + ", ");
        }
        bw.write("\t\t" + stringBuffer.substring(0, stringBuffer.length() - 2));
        bw.newLine();
        bw.write("\t</sql >");
        bw.newLine();
    }

    /**
     * 通用结果映射
     *
     * @param tableInfo
     * @param bw
     * @throws IOException
     */
    private static void constructCommonResultMap(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        bw.write("\t<!-- 通用查询映射结果 -->");
        bw.newLine();
        bw.write("\t<resultMap id=\"BaseResultMap\" type=\"" + Constants.PACKAGE_PO + "." + tableInfo.getBeanName() + "\">");
        bw.newLine();
        Map<String, List<FieldInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
        // 判断主键是否只有一个
        List<FieldInfo> fieldInfoList = keyIndexMap.get("PRIMARY");
        boolean doPrimary = false;
        String primaryKeyName = "";
        if (null != fieldInfoList) {
            int size = fieldInfoList.size();
            // 如果主键只有一个，打印 id 列
            if (size == 1) {
                FieldInfo fieldInfo = fieldInfoList.get(0);
                bw.write("\t\t<!--" + fieldInfo.getComment() + "-->\n");
                bw.write("\t\t<id column=\"" + fieldInfo.getFieldName() + "\" property=\"" + fieldInfo.getPropertyName() + "\"/>");
                bw.newLine();
                doPrimary = true;
                primaryKeyName = fieldInfo.getPropertyName();
            }
        }
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            // 如果主键只有一个
            if (doPrimary) {
                if (fieldInfo.getPropertyName().equals(primaryKeyName)) {
                    continue;
                }
            }
            // 如果主键不只一个 打印 result
            bw.write("\t\t<!--" + fieldInfo.getComment() + "-->\n");
            bw.write("\t\t<result column=\"" + fieldInfo.getFieldName() + "\" property=\"" + fieldInfo.getPropertyName() + "\"/>");
            bw.newLine();
        }
        bw.write("\t</resultMap >");
        bw.newLine();
    }
}
