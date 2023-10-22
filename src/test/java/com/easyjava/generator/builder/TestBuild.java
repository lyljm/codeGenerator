package com.easyjava.generator.builder;

import com.easyjava.generator.Bean.TableInfo;
import com.easyjava.generator.utils.JDBCUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

@Slf4j
public class TestBuild {
    @Test
    public void testReadTable(){
        long start = System.currentTimeMillis();
        Connection connection = JDBCUtils.getConnection();
        List<TableInfo> tableInfoList = BuildTable.readTables();
        JDBCUtils.closeConnection(connection);
        long end = System.currentTimeMillis();
        log.info("花费时间{}",end-start);
    }

    @Test
    public void testReadFieldInfo(){
        TableInfo tableInfo = new TableInfo();
        tableInfo.setTableName("campus_post");
        BuildTable.readFieldsInfo(tableInfo);
    }

    @Test
    public void testBuildPo(){
        long start = System.currentTimeMillis();
        Connection connection = JDBCUtils.getConnection();
        List<TableInfo> tableInfoList = BuildTable.readTables();
        BuildPo buildPo = new BuildPo();
        buildPo.execute(tableInfoList);
        JDBCUtils.closeConnection(connection);
        long end = System.currentTimeMillis();
        log.info("花费时间{}",end-start);
    }
}
