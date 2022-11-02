package crom.rental.entity.renter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crom.rental.entity.common.Identity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.*;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberRenter extends Identity {
  @Id
  private String id;
  private String name;
  private String relationship;
  private String roomNumber;

  @ManyToOne(fetch = LAZY)
  private MasterRenter masterRenter;

}
