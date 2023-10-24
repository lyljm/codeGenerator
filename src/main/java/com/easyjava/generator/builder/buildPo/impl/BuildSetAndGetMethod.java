package com.easyjava.generator.builder.buildPo.impl;

import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.buildPo.IBuildSetAndGetMethod;
import com.easyjava.generator.utils.StrUtils;

import java.io.BufferedWriter;
import java.io.IOException;

public class BuildSetAndGetMethod implements IBuildSetAndGetMethod {
    @Override
    public void createSetAndGetMethod(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            String tempField = StrUtils.firstLetter2Upper(fieldInfo.getPropertyName());
            String propertyName = fieldInfo.getPropertyName();
            // set方法
            bw.write("\tpublic void set" + tempField + "(" + fieldInfo.getJavaType() + " " + propertyName + ") {");
            bw.newLine();
            bw.write("\t\tthis." + propertyName + " = " + propertyName + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            // get方法
            bw.write("\tpublic " + fieldInfo.getJavaType() + " get" + tempField + "() {");
            bw.newLine();
            bw.write("\t\treturn this." + fieldInfo.getPropertyName() + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
        }
    }
}
