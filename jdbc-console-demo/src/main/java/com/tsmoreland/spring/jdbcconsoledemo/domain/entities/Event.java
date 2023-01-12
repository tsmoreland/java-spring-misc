package com.tsmoreland.spring.jdbcconsoledemo.domain.entities;

import com.tsmoreland.spring.jdbcconsoledemo.domain.common.AuditableEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Event extends AuditableEntity {

    @NonNull
    private UUID eventId;
    @NonNull
    public String name;
    public int price;
    public String artist;
    @NonNull
    public Instant date;
    public String description;
    public String imageUrl;
    @NonNull
    public UUID categoryId;
    public Category category;

}
