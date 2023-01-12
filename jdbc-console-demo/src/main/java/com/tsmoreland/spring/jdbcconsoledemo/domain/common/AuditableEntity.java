package com.tsmoreland.spring.jdbcconsoledemo.domain.common;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class AuditableEntity {

    public String CreatedBy;
    @NonNull
    public Instant CreatedDate;
    public String LastModifiedBy ;
    public Instant lastModifiedDate;
}
