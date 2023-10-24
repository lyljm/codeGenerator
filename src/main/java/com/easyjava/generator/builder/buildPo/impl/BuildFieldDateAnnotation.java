package com.easyjava.generator.builder.buildPo.impl;

import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.buildPo.IBuildFieldAnnotation;

import java.io.BufferedWriter;
import java.io.IOException;

// 日期类型
public class BuildFieldDateAnnotation implements IBuildFieldAnnotation {

    @Override
    public void createClassImport(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        if (tableInfo.getHaveDate()) {
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

    @Override
    public void createFieldAnnotation(BufferedWriter bw, FieldInfo fieldInfo) throws IOException {
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
    }
}
