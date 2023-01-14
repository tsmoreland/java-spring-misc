package com.tsmoreland.spring.jdbcdemo.application.contracts.persistence;

import com.tsmoreland.spring.jdbcdemo.domain.common.Page;
import com.tsmoreland.spring.jdbcdemo.domain.entities.Category;

import java.util.List;

public interface CategoryRepository extends Repository<Category> {
    Page<Category> getPage(int pageNumber, int pageSize, boolean includeEvents);
    List<Category> getAll(boolean includeEvents);
}
