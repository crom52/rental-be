package crom.rental.entity.renter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crom.rental.constant.ContractType;
import crom.rental.entity.common.Identity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
public class MasterRenter extends Identity {
  private String id;
  private String name;
  private String roomNumber;
  private ContractType contractType;
  private Instant contractDate;
  private Instant stayingStartDate;
  private Instant stayingEndDate;

  //  @JsonManagedReference(value = "masterRenter")
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "masterRenter", cascade = CascadeType.ALL)
  private List<MemberRenter> memberRenters = new ArrayList<>();

}
