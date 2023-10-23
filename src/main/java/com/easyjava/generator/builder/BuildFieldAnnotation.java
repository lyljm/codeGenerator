package com.easyjava.generator.builder;

import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

public interface BuildFieldAnnotation {
    // void createBean(TableInfo tableInfo, BufferedWriter bw) throws IOException;

    // void createSetAndGetMethod(TableInfo tableInfo, BufferedWriter bw) throws IOException;

    // void createIgnoreImport(TableInfo tableInfo, BufferedWriter bw) throws IOException;

    void createClassImport(BufferedWriter bw, TableInfo tableInfo) throws IOException;

    void createFieldAnnotation(BufferedWriter bw, FieldInfo fieldInfo) throws IOException;

    // void createClassComment(BufferedWriter bw, String classComment) throws IOException;
    //
    // void createFieldComment(BufferedWriter bw, String fieldComment) throws IOException;

}
