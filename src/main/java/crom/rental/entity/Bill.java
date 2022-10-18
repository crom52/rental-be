package crom.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import static java.time.Instant.now;

@Entity
@Table(schema = "rental", name = "bill")
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Jacksonized
public class Bill extends BaseEntity implements Serializable {
    @Id
    private Long id;
    private String oldElecNumber;
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
    private Integer rentalPeriod;
    private Integer roomNumber;

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


