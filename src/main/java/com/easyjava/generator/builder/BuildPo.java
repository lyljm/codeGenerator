package com.easyjava.generator.builder;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;

import java.io.*;
import java.util.Date;
import java.util.List;

public class BuildPo extends BaseBuild {

    public void execute(List<TableInfo> tableInfoList) {
        tableInfoList.forEach(tableInfo -> {
            doExecute(tableInfo, Constants.PATH_PO, tableInfo.getBeanName() + SUFFIX_JAVA);
        });
    }

    @Override
    public void construct(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        bw.write("package " + Constants.PACKAGE_PO + ";");
        bw.newLine();
        bw.newLine();
        createClassImport(bw, tableInfo);
        createIgnoreImport(tableInfo, bw);
        bw.newLine();
        bw.newLine();
        createClassComment(bw, tableInfo.getComment());
        bw.write("public class " + tableInfo.getBeanName() + " implements Serializable {");
        bw.newLine();
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            bw.newLine();
            createFieldComment(bw, fieldInfo.getComment());
            createFieldAnnotation(bw, fieldInfo);
            bw.write("\tprivate " + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName() + ";");
            bw.newLine();
        }
        bw.write("}");
    }

    private static void createIgnoreImport(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        String[] ignoreFields = Constants.IGNORE_BEAN_toJSON_FIELD.split(",");
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            if (ArrayUtil.contains(ignoreFields, fieldInfo.getPropertyName())) {
                bw.write(Constants.IGNORE_BEAN_toJSON_IMPORT);
                break;
            }
        }
    }

    private void createClassImport(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("import java.io.Serializable;");
        bw.newLine();
        if (tableInfo.getHaveDateTime()) {
            bw.write("import java.time.LocalDateTime;");
            bw.newLine();
        }
        if (tableInfo.getHaveDate()) {
            bw.write("import java.util.Date;");
            bw.newLine();
        }
        if (tableInfo.getHaveBigDecimal()) {
            bw.write("import java.math.BigDecimal;");
            bw.newLine();
        }
        if (tableInfo.getHaveDateTime()) {
            if (Constants.BEAN_DATE_SERIALIZE_OPEN) {
                bw.write(Constants.BEAN_DATE_SERIALIZE_IMPORT);
                bw.newLine();
            }
            if (Constants.BEAN_DATE_DESERIALIZE_OPEN) {
                bw.write(Constants.BEAN_DATE_DESERIALIZE_IMPORT);
                bw.newLine();
            }
        }
    }

    private void createClassComment(BufferedWriter bw, String classComment) throws IOException {
        bw.write("/**");
        bw.newLine();
        bw.write(" * @description: " + classComment);
        bw.newLine();
        bw.write(" * @author: " + Constants.COMMENT_AUTHOR);
        bw.newLine();
        bw.write(" * @date: " + DateUtil.format(new Date(), "yyyy-MM-dd"));
        bw.newLine();
        bw.write(" */");
        bw.newLine();
    }

    private void createFieldComment(BufferedWriter bw, String fieldComment) throws IOException {
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * " + fieldComment);
        bw.newLine();
        bw.write("\t */");
        bw.newLine();
    }

    private void createFieldAnnotation(BufferedWriter bw, FieldInfo fieldInfo) throws IOException {
        // 日期类型
        if ("Date".equals(fieldInfo.getJavaType())) {
            if (Constants.BEAN_DATE_SERIALIZE_OPEN) {
                bw.write("\t" + Constants.BEAN_DATE_SERIALIZE_EXPRESSION);
                bw.newLine();
            }
            if (Constants.BEAN_DATE_DESERIALIZE_OPEN) {
                bw.write("\t" + Constants.BEAN_DATE_DESERIALIZE_EXPRESSION);
                bw.newLine();
            }
        }
        // 时间类型
        if ("LocalDateTime".equals(fieldInfo.getJavaType())) {
            if (Constants.BEAN_DATE_SERIALIZE_OPEN) {
                bw.write("\t" + Constants.BEAN_DATETIME_SERIALIZE_EXPRESSION);
                bw.newLine();
            }
            if (Constants.BEAN_DATE_DESERIALIZE_OPEN) {
                bw.write("\t" + Constants.BEAN_DATETIME_DESERIALIZE_EXPRESSION);
                bw.newLine();
            }
        }
        if (ArrayUtil.contains(Constants.IGNORE_BEAN_toJSON_FIELD.split(","), fieldInfo.getFieldName())) {
            bw.write("\t" + Constants.IGNORE_BEAN_toJSON_EXPRESSION);
            bw.newLine();
        }


    }
}
