package com.tsmoreland.spring.jdbcdemo.domain.entities;

import com.tsmoreland.spring.jdbcdemo.domain.common.AuditableEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Category extends AuditableEntity {

    @NonNull
    public UUID categoryId;
    @NonNull
    public String name;
    public List<Event> events;
}
