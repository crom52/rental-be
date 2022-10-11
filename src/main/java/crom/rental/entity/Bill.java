package crom.rental.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(schema = "rental", name = "bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bill {
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

//    @Builder
//    public Bill(Timestamp createdTime, Timestamp updatedTime, String createdBy, String updatedBy ) {
//        super(createdTime, updatedTime, createdBy, updatedBy);
//    }
}
