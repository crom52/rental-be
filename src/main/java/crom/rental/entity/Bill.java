package crom.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static java.time.Instant.now;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "rental", name = "bill")
@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill extends BaseEntity {
    @Id
    private String id;
    private Integer oldElecNumber;
    private Integer newElecNumber;
    private Integer usedElec;
    private Double elecPrice;
    private Double totalElecMoney;
    private Integer oldWaterNumber;
    private Integer newWaterNumber;
    private Integer usedWater;
    private Double waterPrice;
    private Double totalWaterMoney;
    private Double rentalPrice;
    private Double otherPrice;
    private Double totalMoney;

    @NonNull
    private String rentalPeriod;

    @NonNull
    private String roomNumber;

    public <T extends BaseEntity> T setBaseEntity(T childEntity) {
        if (childEntity.getClass().getSuperclass() == BaseEntity.class) {
            childEntity.setCreatedBy("SYSTEM");
            childEntity.setUpdatedBy("SYSTEM");
            childEntity.setCreatedTime(now());
            childEntity.setUpdatedTime(now());
        }
        return childEntity;
    }

}


