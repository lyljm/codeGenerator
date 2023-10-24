package com.easyjava.generator.builder.buildPo.impl;

import cn.hutool.core.util.ArrayUtil;
import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.buildPo.IBuildFieldAnnotation;

import java.io.BufferedWriter;
import java.io.IOException;

//转换为Json时忽略的字段
public class BuildFieldIgnoreAnnotation implements IBuildFieldAnnotation {
    @Override
    public void createClassImport(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        String[] ignoreFields = Constants.IGNORE_BEAN_toJSON_FIELD.split(",");
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            if (ArrayUtil.contains(ignoreFields, fieldInfo.getPropertyName())) {
                bw.write(Constants.IGNORE_BEAN_toJSON_IMPORT);
                break;
            }
        }
    }

    @Override
    public void createFieldAnnotation(BufferedWriter bw, FieldInfo fieldInfo) throws IOException {
        if (ArrayUtil.contains(Constants.IGNORE_BEAN_toJSON_FIELD.split(","), fieldInfo.getPropertyName())) {
            bw.write("\t" + Constants.IGNORE_BEAN_toJSON_EXPRESSION);
            bw.newLine();
        }
    }
}
