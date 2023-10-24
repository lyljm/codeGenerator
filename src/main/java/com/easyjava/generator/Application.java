package com.easyjava.generator;

import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.buildPo.BuildPo;
import com.easyjava.generator.builder.ConstructTableInfos;
import com.easyjava.generator.builder.buildPo.impl.*;
import com.easyjava.generator.builder.buildXml.BuildXml;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Application {
    public static void main(String[] args) {
        List<TableInfo> tableInfoList = ConstructTableInfos.readTables();
        BuildPo buildPo = new BuildPo();
        buildPo.registerFiledComment(new BuildFieldComment());
        buildPo.registerBeanComment(new BuildBeanComment());
        buildPo.registerSetAndGetMethod(new BuildSetAndGetMethod());
        buildPo.registerFieldAnnotation(
                new BuildFieldDateAnnotation(),
                new BuildFieldDateTimeAnnotation(),
                new BuildFieldIgnoreAnnotation()
        );
        buildPo.execute(tableInfoList);
        BuildXml buildXml = new BuildXml();
        buildXml.execute(tableInfoList);
    }
}
