package org.marine.simpulationwebprogram.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        // Log the exception message and stack trace for debugging
        System.err.println("Error occurred: " + e.getMessage());
        e.printStackTrace();
        return "Error occurred, please check the logs.";
    }
}
