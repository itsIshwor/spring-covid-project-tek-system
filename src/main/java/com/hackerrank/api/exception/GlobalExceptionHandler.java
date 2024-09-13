package com.hackerrank.api.exception;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorDTO {
    String message;
    String statusCode;

}

@RestControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleElementNotFoundException(ElementNotFoundException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), HttpStatus.BAD_REQUEST.name()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ElementWithSameIDAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleElementAlreadyExistException(ElementWithSameIDAlreadyExistsException e) {
        return new ResponseEntity<>(new ErrorDTO(e.getMessage(), HttpStatus.BAD_REQUEST.name()), HttpStatus.BAD_REQUEST);
    }
}
