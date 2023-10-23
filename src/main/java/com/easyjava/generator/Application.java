package com.easyjava.generator;

import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.impl.BuildPo;
import com.easyjava.generator.builder.InitInfo;
import com.easyjava.generator.utils.JDBCUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.List;

@Slf4j
public class Application {
    public static void main(String[] args) {
        List<TableInfo> tableInfoList = InitInfo.readTables();
        BuildPo buildPo = new BuildPo();
        buildPo.execute(tableInfoList);

    }
}
