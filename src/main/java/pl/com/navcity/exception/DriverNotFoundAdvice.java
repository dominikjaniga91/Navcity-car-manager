package pl.com.navcity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DriverNotFoundAdvice {


    @ExceptionHandler(DriverNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getBookNotFoundHandler(DriverNotFoundException ex){

        return ex.getMessage();
    }
}
