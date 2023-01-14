package com.tsmoreland.spring.jdbcdemo.application.contracts.persistence;

import com.tsmoreland.spring.jdbcdemo.domain.common.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Repository<T> {
    Optional<T> GetById(UUID id);
    List<T> GetAll();
    Page<T> GetPage(int pageNumber, int pageSize);
    T Add(T entity);
    void Update(T entity);
    void Delete(T entity);
}
