package br.com.productms.infrastructure.repository.exception;

import br.com.productms.domain.model.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity notFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {MissingArgumentsException.class, InvalidPriceException.class})
    protected ResponseEntity<ResponseStatus> sendDetailedExceptionMessage(MissingArgumentsException missingArgumentsException){

        ResponseStatus responseStatus = ResponseStatus.builder()
                .httpStatus(missingArgumentsException.getHttpStatus())
                .message(missingArgumentsException.getMessage())
                .build();

        return ResponseEntity.badRequest().body(responseStatus);
    }
}
