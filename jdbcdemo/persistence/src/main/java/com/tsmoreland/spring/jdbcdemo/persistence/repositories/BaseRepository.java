package com.tsmoreland.spring.jdbcdemo.persistence.repositories;

import com.tsmoreland.spring.jdbcdemo.application.contracts.persistence.Repository;
import com.tsmoreland.spring.jdbcdemo.domain.common.Page;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseRepository<T> implements Repository<T> {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<T> rowMapper;
    private final SqlProvider<T> sqlProvider;

    public BaseRepository(
        JdbcTemplate jdbcTemplate,
        RowMapper<T> rowMapper,
        SqlProvider<T> sqlProvider) {

        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.sqlProvider = sqlProvider;
    }

    @Override
    public Optional<T> getById(UUID id) {
        T value = jdbcTemplate.queryForObject(sqlProvider.getById(), rowMapper, id);
        return value != null
            ? Optional.of(value)
            : Optional.empty();
    }

    @Override
    public List<T> getAll() {
        return jdbcTemplate.query(sqlProvider.getAll(), rowMapper);
    }

    @Override
    public Page<T> getPage(int pageNumber, int pageSize) {
        List<T> items = jdbcTemplate.query(sqlProvider.getPage(), rowMapper,
            (pageNumber - 1) * pageSize, pageSize);

        Long totalCountValue = jdbcTemplate
            .queryForObject(sqlProvider.getTotalCount(),
                (ResultSet rs, int rowNumber) -> rs.getLong(0));
        int totalCount = totalCountValue != null
            ? totalCountValue.intValue()
            : 0;
        int totalPages = getTotalPagesFromPageSizeAndTotalCount(totalCount, pageSize);
        return new Page<>(pageNumber, pageSize, totalPages, totalCount, items);
    }

    @Override
    public T add(T entity) {
        var keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> sqlProvider.prepareAddStatement(conn, entity), keyholder);
        // TODO: replace with something like data error, i.e. more specific
        return getById(getId(entity)).orElseThrow(() -> new RuntimeException("object not found"));
    }

    @Override
    public void update(T entity) {
        var keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> sqlProvider.prepareUpdateStatement(conn, entity), keyholder);
    }

    @Override
    public void delete(T entity) {
        jdbcTemplate.update(sqlProvider.delete(), getId(entity));
    }

    protected int getTotalPagesFromPageSizeAndTotalCount(int totalCount, int pageSize) {
        return pageSize > 0
            ? (int)Math.ceil(totalCount * 1.0 / pageSize)
            : 0;
    }

    protected abstract UUID getId(T entity);
}
