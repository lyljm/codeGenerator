package com.easyjava.generator.builder;

import com.easyjava.generator.Bean.TableInfo;

import java.io.BufferedWriter;

/**
 * 创建bean声明
 */
public interface BuildBeanAnnotation {
    /**
     * 创建类声明
     * @param bw
     * @param tableInfo
     */
    void createClassAnnotation(BufferedWriter bw, TableInfo tableInfo);

    /**
     * 创建类声明导入
     * @param bw
     * @param tableInfo
     */
    void createClassAnnotationImport(BufferedWriter bw, TableInfo tableInfo);
}
