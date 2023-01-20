package com.tsmoreland.spring.jdbcdemo.persistence.repositories;

import com.tsmoreland.spring.jdbcdemo.domain.common.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SqlProvider<T> {
    String getById();
    String getAll();
    String getPage();
    PreparedStatement prepareAddStatement(Connection connection, T entity) throws SQLException;
    PreparedStatement prepareUpdateStatement(Connection connection, T entity) throws SQLException;
    String delete();
    String getTotalCount();
}
