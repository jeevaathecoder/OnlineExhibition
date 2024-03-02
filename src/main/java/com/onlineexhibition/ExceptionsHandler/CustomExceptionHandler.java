package com.onlineexhibition.ExceptionsHandler;

import com.onlineexhibition.response.CustomErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        //create the response
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .message("Validation Failed")
                .errorMessages(errorMessages)
                .build();

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(IllegalArgumentException ex) {
        //create the response
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .message(ex.getMessage())
                .build();

        return  ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(RuntimeException ex) {
        //create the response
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .message(ex.getMessage())
                .build();

        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationException(UsernameNotFoundException ex) {
        //create the response
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .message(ex.getMessage())
                .build();

        return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .message("Error in the application. Communicate with Backend Developers")
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .message("Required request body is missing")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
