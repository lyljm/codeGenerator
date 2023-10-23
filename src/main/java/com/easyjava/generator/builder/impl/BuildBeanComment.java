package com.easyjava.generator.builder.impl;

import cn.hutool.core.date.DateUtil;
import com.easyjava.generator.Bean.Constants;
import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.IBuildBeanComment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

public class BuildBeanComment implements IBuildBeanComment {
    /**
     * 创建类Comment
     * @param bw
     * @param tableInfo
     * @throws IOException
     */
    @Override
    public void createBeanComment(BufferedWriter bw, TableInfo tableInfo) throws IOException {
        bw.write("/**");
        bw.newLine();
        bw.write(" * @description: " + tableInfo.getComment());
        bw.newLine();
        bw.write(" * @author: " + Constants.COMMENT_AUTHOR);
        bw.newLine();
        bw.write(" * @date: " + DateUtil.format(new Date(), "yyyy-MM-dd"));
        bw.newLine();
        bw.write(" */");
        bw.newLine();
    }
}
