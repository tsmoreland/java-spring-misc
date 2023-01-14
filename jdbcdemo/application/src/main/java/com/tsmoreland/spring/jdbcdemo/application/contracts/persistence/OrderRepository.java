package com.tsmoreland.spring.jdbcdemo.application.contracts.persistence;

import com.tsmoreland.spring.jdbcdemo.domain.common.Page;
import com.tsmoreland.spring.jdbcdemo.domain.entities.Order;

import java.time.Instant;

public interface OrderRepository extends Repository<Order> {
    Page<Order> getPagedOrdersForMonth(Instant date, int pageNumber, int pageSize);
    int getTotalCountOfOrdersForMonth(Instant date);

}
