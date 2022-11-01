package crom.rental.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    private Instant createdTime;
    private Instant updatedTime;
    private String createdBy;
    private String updatedBy;


}
