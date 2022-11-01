package crom.rental.entity.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Identity extends BaseEntity {
  private String fullName;
  private String socialId;
  private String permanentAddress;
  private String placeOrigin;
  private String phoneNumber;
  private boolean isTempResidenceVerified;
}
