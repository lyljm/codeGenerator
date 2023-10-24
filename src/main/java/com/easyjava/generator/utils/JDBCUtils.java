package com.easyjava.generator.utils;

import com.easyjava.generator.handler.IResultSetHandler;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public abstract class JDBCUtils {
    private static String driverName = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static Connection conn = null;

    static {
        try {
            driverName = PropertiesUtils.getString("db.datasource.driver-class-name");
            url = PropertiesUtils.getString("db.datasource.url");
            username = PropertiesUtils.getString("db.datasource.username");
            password = PropertiesUtils.getString("db.datasource.password");
            Class.forName(driverName);
        } catch (Exception e) {
            log.info("未加载到驱动: {}\n{}", driverName, e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.info("数据库连接失败\n{}", e.getMessage());
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                log.info("关闭connection失败");
            }
        }
    }

    public static void closePsAndRs(PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
            if (ps != null) {
                ps.close();
                ps = null;
            }
        } catch (SQLException e) {
            log.info("close ps and rs failed: {}", e.getCause());
        }
    }

    public static void executeQuery(String sql, IResultSetHandler resultSetHandler) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSetHandler.handle(resultSet);
        } catch (SQLException e) {
            log.info("sql执行错误：{} \n{}", sql, e);
        } finally {
            JDBCUtils.closePsAndRs(preparedStatement, resultSet);
        }
    }
}
