package crom.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;
import java.sql.Timestamp;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "rental", name = "bill")
//@Getter
//@Setter
//@JsonIgnoreProperties(ignoreUnknown = true)
//@Jacksonized
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill extends BaseEntity {
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

    public Bill() {
        super();
    }

    public <T extends BaseEntity> T setBaseEntity(T childEntity) {
        if (childEntity.getClass().getSuperclass() == BaseEntity.class) {
            childEntity.setCreatedBy("SYSTEM");
            childEntity.setUpdatedBy("SYSTEM");
            childEntity.setCreatedTime(from(now()));
            childEntity.setUpdatedTime(from(now()));
        }
        return childEntity;
    }

}


