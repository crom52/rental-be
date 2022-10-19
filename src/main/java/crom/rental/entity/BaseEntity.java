package crom.rental.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    private Instant createdTime;
    private Instant updatedTime;

    private String createdBy;
    private String updatedBy;

}
