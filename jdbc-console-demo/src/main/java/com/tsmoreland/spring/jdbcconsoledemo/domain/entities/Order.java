package com.tsmoreland.spring.jdbcconsoledemo.domain.entities;

import com.tsmoreland.spring.jdbcconsoledemo.domain.common.AuditableEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Order extends AuditableEntity {
    @NonNull
    public UUID id;
    @NonNull
    public UUID eventId;
    @NonNull
    public UUID UserId;
    public int orderTotal;
    @NonNull
    public Instant orderPlaced;
    public boolean orderPaid;
}
