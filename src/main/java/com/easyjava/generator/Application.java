package com.easyjava.generator;

import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.builder.BuildPo;
import com.easyjava.generator.builder.BuildTable;
import com.easyjava.generator.utils.JDBCUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.List;

@Slf4j
public class Application {
    public static void main(String[] args) {
        Connection connection = JDBCUtils.getConnection();
        List<TableInfo> tableInfoList = BuildTable.readTables();
        JDBCUtils.closeConnection(connection);
        BuildPo buildPo = new BuildPo();
        buildPo.execute(tableInfoList);

    }
}
