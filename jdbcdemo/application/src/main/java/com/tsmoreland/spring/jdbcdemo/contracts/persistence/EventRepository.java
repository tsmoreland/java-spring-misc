package com.tsmoreland.spring.jdbcdemo.contracts.persistence;

import com.tsmoreland.spring.jdbcdemo.domain.entities.Event;

import java.time.Instant;

public interface EventRepository extends Repository<Event> {
    boolean IsEventNameAndDateUnique(String Name, Instant instant);
}
