package com.tsmoreland.spring.jdbcdemo.persistence.repositories;

import com.tsmoreland.spring.jdbcdemo.domain.common.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SqlProvider<T> {
    String getById();
    String getAll();
    String getPage();
    String add();
    String update();
    String delete();
}
