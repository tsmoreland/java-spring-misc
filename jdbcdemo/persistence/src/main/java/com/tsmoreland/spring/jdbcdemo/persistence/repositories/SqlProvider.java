package com.tsmoreland.spring.jdbcdemo.persistence.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlProvider<T> {
    String getById();
    String getAll();
    String getPage();
    PreparedStatement prepareAddStatement(Connection connection, T entity) throws SQLException;
    PreparedStatement prepareUpdateStatement(Connection connection, T entity) throws SQLException;
    String delete();
    String getTotalCount();
}
