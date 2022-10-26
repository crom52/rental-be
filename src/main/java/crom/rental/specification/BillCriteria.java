package crom.rental.specification;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillCriteria {
    private String key;
    private String operation;
    private Object value;
}
