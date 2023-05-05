package br.com.mongodbwithspringboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleStudentNotFoundException
            (StudentNotFoundException exception) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ExceptionResponse exceptionResponse =
                 new ExceptionResponse(httpStatus.value(), exception.getMessage());

        return ResponseEntity.status(httpStatus).body(exceptionResponse);

    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequestException
            (BadRequestException exception) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(httpStatus.value(), exception.getMessage());

        return ResponseEntity.status(httpStatus).body(exceptionResponse);

    }

    @ExceptionHandler(InvalidDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleInvalidDataException
            (InvalidDataException exception) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionResponse exceptionResponse =
                new ExceptionResponse(httpStatus.value(), exception.getMessage());

        return ResponseEntity.status(httpStatus).body(exceptionResponse);

    }

}
