package com.rooftop.seniorityboost.challenge.models.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoTextFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> notFound(NoTextFoundException ex, WebRequest request) {
        ErrorMessage apiError = new ErrorMessage(
                true, "Text not found", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorMessage>(
                apiError, new HttpHeaders(), apiError.getCode());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> badRequest(BadRequestException ex, WebRequest request) {
        ErrorMessage apiError = new ErrorMessage(
                true, "Bad input, check the parameters", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<ErrorMessage>(
                apiError, new HttpHeaders(), apiError.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handleAll(Exception ex, WebRequest request) {
        ErrorMessage apiError = new ErrorMessage(
                true, "An error occurred when processing the text",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<ErrorMessage>(
                apiError, new HttpHeaders(), apiError.getCode());
    }

}