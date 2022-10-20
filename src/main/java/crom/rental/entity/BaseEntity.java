package crom.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

//@Getter
//@Setter
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BaseEntity {
    private Timestamp createdTime;
    private Timestamp updatedTime;

    private String createdBy;
    private String updatedBy;

}
