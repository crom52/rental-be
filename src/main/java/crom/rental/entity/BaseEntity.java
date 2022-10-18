package crom.rental.entity;

import java.time.Instant;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
  private Instant createdTime;
  private Instant updatedTime;

  private String createdBy;
  private String updatedBy;

}
