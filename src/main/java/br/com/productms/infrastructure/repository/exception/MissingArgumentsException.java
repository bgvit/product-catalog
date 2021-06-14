package br.com.productms.infrastructure.repository.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MissingArgumentsException extends DetailedException{

    public MissingArgumentsException(HttpStatus httpStatus, String message) {
        super(message, httpStatus);
    }
}
