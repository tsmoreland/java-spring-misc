package com.tsmoreland.spring.jdbcdemo.application.contracts.persistence;

import com.tsmoreland.spring.jdbcdemo.domain.common.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {
    Optional<T> getById(UUID id);
    List<T> getAll();
    Page<T> getPage(int pageNumber, int pageSize);
    T add(T entity);
    void update(T entity);
    void delete(T entity);
}
