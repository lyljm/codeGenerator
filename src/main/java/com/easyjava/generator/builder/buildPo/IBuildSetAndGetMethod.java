package com.easyjava.generator.builder.buildPo;

import com.easyjava.generator.Bean.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IBuildSetAndGetMethod {

    void createSetAndGetMethod(TableInfo tableInfo, BufferedWriter bw) throws IOException;
}
