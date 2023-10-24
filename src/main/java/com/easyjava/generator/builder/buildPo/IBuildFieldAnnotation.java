package com.easyjava.generator.builder.buildPo;

import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IBuildFieldAnnotation {

    void createClassImport(BufferedWriter bw, TableInfo tableInfo) throws IOException;

    void createFieldAnnotation(BufferedWriter bw, FieldInfo fieldInfo) throws IOException;

}
