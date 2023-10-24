package com.easyjava.generator.builder.buildPo.impl;

import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.builder.buildPo.IBuildFieldComment;

import java.io.BufferedWriter;
import java.io.IOException;

public class BuildFieldComment implements IBuildFieldComment {

    @Override
    public void createFieldComment(BufferedWriter bw, FieldInfo fieldInfo) throws IOException {
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * " + fieldInfo.getFieldName());
        bw.newLine();
        bw.write("\t */");
        bw.newLine();
    }
}
