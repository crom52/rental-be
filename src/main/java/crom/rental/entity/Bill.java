package crom.rental.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import static java.time.Instant.now;

@Entity
@Table(schema = "rental", name = "bill")
@Getter
@Setter
public class Bill extends BaseEntity {
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


