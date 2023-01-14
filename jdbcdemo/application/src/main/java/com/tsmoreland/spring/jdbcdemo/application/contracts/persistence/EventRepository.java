package com.tsmoreland.spring.jdbcdemo.application.contracts.persistence;

import com.tsmoreland.spring.jdbcdemo.domain.entities.Event;

import java.time.Instant;

public interface EventRepository extends Repository<Event> {
    boolean isEventNameAndDateUnique(String Name, Instant instant);
}
