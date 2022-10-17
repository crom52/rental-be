package crom.rental.common;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ResultResponse {
    private final HttpStatus status;
    private final Object data;
    private final String message;
}
