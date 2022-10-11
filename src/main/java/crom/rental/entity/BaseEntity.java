package crom.rental.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import static  java.sql.Timestamp.from;

import java.sql.Timestamp;
import static  java.time.Instant.now;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity {
    @Builder.Default
    private Timestamp createdTime = from(now());

    @Builder.Default
    private Timestamp updatedTime = from(now());

    private String createdBy;
    private String updatedBy;

}
