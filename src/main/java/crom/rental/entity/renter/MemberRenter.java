package crom.rental.entity.renter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crom.rental.entity.common.Identity;
import javax.persistence.FetchType;
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
public class MemberRenter extends Identity {
  private String id;
  private String name;
  private String relationship;

  //  @JsonBackReference(value = "masterRenter")
  @ManyToOne(fetch = FetchType.LAZY)
  private MasterRenter masterRenter;

}
