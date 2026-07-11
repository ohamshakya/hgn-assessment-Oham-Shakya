package com.himalaya.task.common.advice;

import com.himalaya.task.common.exception.AlreadyExistsException;
import com.himalaya.task.common.exception.ResourceNotFoundException;
import com.himalaya.task.common.exception.ValidationException;
import com.himalaya.task.common.util.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.himalaya.task.common.util.ErrorResponse.buildErrorResponse;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<ResponseWrapper<String>> handleAlreadyExistsException(
            AlreadyExistsException e) {
        log.error("AlreadyExistsException: ", e);
        return buildErrorResponse(e.getMessage(), HttpStatus.CONFLICT);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, ValidationException.class})
    public ResponseEntity<ResponseWrapper<Map<String, String>>> handleValidationExceptions(Exception ex) {
        log.error("handleValidationExceptions", ex);

        BindingResult bindingResult = ex instanceof ValidationException
                ? ((ValidationException) ex).getBindingResult()
                : ((MethodArgumentNotValidException) ex).getBindingResult();

        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ResponseWrapper<Map<String, String>> response = new ResponseWrapper<>(
                errors,
                "Validation errors occurred",
                HttpStatus.BAD_REQUEST.value(),
                false
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ResponseWrapper<String>> handleResourceNotFoundException(
            ResourceNotFoundException e) {
        log.error("ResourceNotFoundException: ", e);
        return buildErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}
