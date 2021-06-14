package br.com.productms.infrastructure.repository.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InvalidPriceException extends DetailedException{

    public InvalidPriceException(HttpStatus httpStatus, String message) {
        super(message, httpStatus);
    }
}
