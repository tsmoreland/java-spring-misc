package com.tsmoreland.spring.jdbcconsoledemo.domain.common;

import java.util.List;

public record Page<T>(int pageNumber, int pageSize, int totalPages, int totalCouut, List<T> items) {

}
