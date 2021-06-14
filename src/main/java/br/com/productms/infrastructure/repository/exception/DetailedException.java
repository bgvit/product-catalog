package br.com.productms.infrastructure.repository.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetailedException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;
}
