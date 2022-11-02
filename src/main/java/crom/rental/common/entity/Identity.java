package crom.rental.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import crom.rental.common.constant.TempResidenceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Identity extends BaseEntity {
  private String fullName;
  private String socialId;
  private String permanentAddress;
  private String placeOrigin;
  private String phoneNumber;
  private TempResidenceStatus tempResidenceStatus;
}
