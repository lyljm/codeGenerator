package com.easyjava.generator.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IResultSetHandler {
    void handle(ResultSet resultSet) throws SQLException;
}
