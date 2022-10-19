package crom.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
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
    @NonNull
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


