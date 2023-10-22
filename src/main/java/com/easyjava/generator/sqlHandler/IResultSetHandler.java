package com.easyjava.generator.sqlHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetHandler {
    void handle(ResultSet resultSet) throws SQLException;
}
