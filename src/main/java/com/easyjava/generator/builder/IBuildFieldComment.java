package com.easyjava.generator.builder;

import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IBuildFieldComment {
    void createFieldComment(BufferedWriter bw, FieldInfo fieldInfo) throws IOException;
}