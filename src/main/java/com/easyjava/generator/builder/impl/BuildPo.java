package com.easyjava.generator.builder.impl;

import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildPo extends BaseBuild {

    private List<BuildFieldAnnotation> buildFieldAnnotationList = new ArrayList<>();

    private List<BuildBeanAnnotation> buildBeanAnnotationList = new ArrayList<>();

    private IBuildFieldComment buildFieldComment;

    private IBuildBeanComment buildBeanComment;

    private IBuildSetAndGetMethod buildSetAndGetMethod;

    @Override
    public void construct(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        bw.write("package " + Constants.PACKAGE_PO + ";");
        bw.newLine();
        bw.newLine();
        createClassImport(bw, tableInfo);
        bw.newLine();
        bw.newLine();
        if (null != buildBeanComment) buildBeanComment.createBeanComment(bw, tableInfo);
        for (BuildBeanAnnotation buildBeanAnnotation : buildBeanAnnotationList) {
            buildBeanAnnotation.createClassAnnotation(bw, tableInfo);
        }
        bw.write("public class " + tableInfo.getBeanName() + " implements Serializable {");
        bw.newLine();
        createField(tableInfo, bw);
        bw.newLine();
        bw.newLine();
        if (null != buildSetAndGetMethod) buildSetAndGetMethod.createSetAndGetMethod(tableInfo, bw);
        bw.write("}");
    }

    public BuildPo() {
        super(Constants.PATH_PO, SUFFIX_JAVA);
    }

    public void registerFieldAnnotation(BuildFieldAnnotation... buildFieldAnnotations) {
        buildFieldAnnotationList.addAll(Arrays.asList(buildFieldAnnotations));
    }

    public void registerBeanAnnotation(BuildBeanAnnotation... buildBeanAnnotations) {
        buildBeanAnnotationList.addAll(Arrays.asList(buildBeanAnnotations));
    }

    public void registerFiledComment(IBuildFieldComment buildFieldComment) {
        this.buildFieldComment = buildFieldComment;
    }

    public void registerBeanComment(IBuildBeanComment buildBeanComment) {
        this.buildBeanComment = buildBeanComment;
    }

    public void registerSetAndGetMethod(IBuildSetAndGetMethod buildSetAndGetMethod) {
        this.buildSetAndGetMethod = buildSetAndGetMethod;
    }

    /**
     * 创建Field、注释、申明
     * @param tableInfo
     * @param bw
     * @throws IOException
     */
    private void createField(TableInfo tableInfo, BufferedWriter bw) throws IOException {
        for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
            bw.newLine();
            // 创建字段注释
            if (null != buildFieldComment) buildFieldComment.createFieldComment(bw, fieldInfo);
            // 创建字段声明
            for (BuildFieldAnnotation buildFieldAnnotation : buildFieldAnnotationList) {
                buildFieldAnnotation.createFieldAnnotation(bw, fieldInfo);
            }
            bw.write("\tprivate " + fieldInfo.getJavaType() + " " + fieldInfo.getPropertyName() + ";");
            bw.newLine();
        }
    }

    /**
     * 创建类文件中的import
     *
     * @param bw
     * @param tableInfo
     * @throws IOException
     */
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
        // 导入类上面的声明
        for (BuildBeanAnnotation buildBeanAnnotation : buildBeanAnnotationList) {
            buildBeanAnnotation.createClassAnnotation(bw, tableInfo);
        }
        // 导入字段上面的声明
        for (BuildFieldAnnotation buildFieldAnnotation : buildFieldAnnotationList) {
            buildFieldAnnotation.createClassImport(bw, tableInfo);
        }
    }

}
