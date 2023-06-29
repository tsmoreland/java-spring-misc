package com.tsmoreland.spring.jdbcdemo.persistence.repositories.mysql;

import com.tsmoreland.spring.jdbcdemo.domain.entities.Event;
import com.tsmoreland.spring.jdbcdemo.persistence.repositories.SqlProvider;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;

@Component
public class EventsSqlProvider implements SqlProvider<Event> {

    private final ZoneId utcZone = ZoneId.of("UTC");

    @Override
    public String getById() {
        return "select * from events where id = ?";
    }

    @Override
    public String getAll() {
        return "select * from events";
    }

    @Override
    public String getPage() {
        return "select * from pets OFFSET ? LIMIT ?";
    }

    @Override
    public PreparedStatement prepareAddStatement(Connection connection, Event entity) throws SQLException {
        var preparedStatement = connection
            .prepareStatement("""
            insert into events
            (event_id, name, price, artist, date, description, image_url, category_id, created_by, created_date, last_modified_by, last_modified_date)
            values
            (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """);

        preparedStatement.setString(1, entity.getEventId().toString());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setInt(3, entity.getPrice());
        preparedStatement.setString(4, entity.getArtist());
        preparedStatement.setDate(5, Date.valueOf(LocalDate.ofInstant(entity.getDate(), utcZone)));
        preparedStatement.setString(6, entity.getDescription());
        preparedStatement.setString(7, entity.getImageUrl());
        preparedStatement.setString(8, entity.getCategoryId().toString());
        preparedStatement.setString(9, entity.getCreatedBy());
        preparedStatement
            .setDate(10, Date.valueOf(LocalDate.ofInstant(entity.getCreatedDate(), utcZone)));
        preparedStatement.setString(11, entity.getLastModifiedBy());
        preparedStatement
            .setDate(12, Date.valueOf(LocalDate.ofInstant(entity.getLastModifiedDate(), utcZone)));
        return preparedStatement;
    }


    @Override
    public PreparedStatement prepareUpdateStatement(Connection connection, Event entity) throws SQLException {
        var preparedStatement = connection
            .prepareStatement("""
            update events
            set
            name = ?,
            price = ?,
            artist = ?,
            image_url = ?,
            category_id = ?,
            last_modified_by = ?,
            last_modified_date = ?
            where id = :id
            """);
        preparedStatement.setString(1,  entity.getName());
        preparedStatement.setInt(2,  entity.getPrice());
        preparedStatement.setString(3,  entity.getArtist());
        preparedStatement.setString(4,  entity.getImageUrl());
        preparedStatement.setString(5,  entity.getCategoryId().toString());
        preparedStatement.setDate(5,  Date.valueOf(LocalDate.ofInstant(entity.getDate(), utcZone)));
        preparedStatement.setString(6,  entity.getDescription());
        preparedStatement.setString(7,  entity.getLastModifiedBy());
        preparedStatement.setDate(8,  Date.valueOf(LocalDate.ofInstant(entity.getLastModifiedDate(), utcZone)));
        return preparedStatement;
    }

    private HashMap<String, Object> CreateParameterMap(Event entity) {
        var parameterMap = new HashMap<String, Object>();
        parameterMap.put("id", entity.getEventId());
        parameterMap.put("name", entity.getName());
        parameterMap.put("price", entity.getPrice());
        parameterMap.put("artist", entity.getArtist());
        parameterMap.put("date", Date.valueOf(LocalDate.ofInstant(entity.getDate(), utcZone)));
        parameterMap.put("description", entity.getDescription());
        parameterMap.put("image_url", entity.getImageUrl());
        parameterMap.put("category_id", entity.getCategoryId().toString());
        parameterMap.put("created_by", entity.getCreatedBy().toString());
        parameterMap.put("created_date", Date.valueOf(LocalDate.ofInstant(entity.getCreatedDate(), utcZone)));
        parameterMap.put("last_modifed_by", entity.getLastModifiedBy().toString());
        parameterMap.put("last_modified_date", Date.valueOf(LocalDate.ofInstant(entity.getLastModifiedDate(), utcZone)));
        return parameterMap;
    }
    @Override
    public String delete() {
        return null;
    }

    @Override
    public String getTotalCount() {
        return null;
    }
}
