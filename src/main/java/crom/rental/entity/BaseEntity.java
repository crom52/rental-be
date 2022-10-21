package crom.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String createdBy;
    private String updatedBy;


}
