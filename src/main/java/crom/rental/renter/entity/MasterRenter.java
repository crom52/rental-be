package crom.rental.renter.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crom.rental.common.constant.ContractType;
import crom.rental.common.entity.Identity;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import javax.persistence.Entity;

import static javax.persistence.FetchType.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(schema = "rental", name = "master_renter")
public class MasterRenter extends Identity {
  @Id
  private String id;
  private String name;
  private String roomNumber;
  private ContractType contractType;
  private Instant contractDate;
  private Instant stayingStartDate;
  private Instant stayingEndDate;

  @JsonIgnore
  @OneToMany(fetch = LAZY, mappedBy = "masterRenter", cascade = ALL, targetEntity = MemberRenter.class)
  private List<MemberRenter> memberRenters = new ArrayList<>();

}
