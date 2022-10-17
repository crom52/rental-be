package crom.rental.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;

@Getter
@Setter
public class BaseEntity {
    private Instant createdTime;
    private Instant updatedTime;

    private String createdBy;
    private String updatedBy;

}
