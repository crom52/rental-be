package crom.rental.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@Setter
@Inheritance
@MappedSuperclass
public class BaseEntity {
    private Instant createdTime;
    private Instant updatedTime;

    private String createdBy;
    private String updatedBy;

}
