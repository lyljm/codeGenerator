package com.easyjava.generator.builder.buildXml;

import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.BaseBuild;

import java.io.BufferedWriter;
import java.io.IOException;

public class BuildXml extends BaseBuild {
    /**
     * 继承该类必须赋值文件路径和文件后缀
     */
    public BuildXml() {
        super(Constants.PATH_PO, SUFFIX_XML);
    }

    @Override
    public void construct(TableInfo tableInfo, BufferedWriter bw) throws IOException {

    }
}
