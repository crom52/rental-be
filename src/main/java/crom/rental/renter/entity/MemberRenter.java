package crom.rental.renter.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crom.rental.common.entity.Identity;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "rental", name = "member_renter")
public class MemberRenter extends Identity {
  @Id
  private String id;
  private String name;
  private String relationship;
  private String roomNumber;
  private Instant stayingStartDate;
  private Instant stayingEndDate;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "master_rental_id")
  private MasterRenter masterRenter;

}
