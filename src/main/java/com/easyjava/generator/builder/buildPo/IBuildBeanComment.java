package com.easyjava.generator.builder.buildPo;

import com.easyjava.generator.Bean.FieldInfo;
import com.easyjava.generator.Bean.TableInfo;

import java.io.BufferedWriter;
import java.io.IOException;

public interface IBuildBeanComment {
    void createBeanComment(BufferedWriter bw, TableInfo tableInfo) throws IOException;
}
