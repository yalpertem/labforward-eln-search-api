package com.labforward.api.eln.common.advisor;

import com.labforward.api.eln.common.error.ErrorResponse;
import com.labforward.api.eln.common.error.ValidationError;
import com.labforward.api.eln.common.exception.ArgumentConflictException;
import com.labforward.api.eln.common.exception.BadRequestException;
import com.labforward.api.eln.common.exception.CannotProcessException;
import com.labforward.api.eln.common.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the exceptions thrown and sets errors and HTTP Status codes in response
 */
@RestControllerAdvice
public class RestControllerExceptionAdvisor {

    public static final String VALIDATION_ERROR_MESSAGE = "Entity validation error";

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(
            BadRequestException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            NotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CannotProcessException.class)
    public ResponseEntity<ErrorResponse> handleCannotProcessException(
            CannotProcessException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ArgumentConflictException.class)
    public ResponseEntity<ErrorResponse> handleArgumentConflictException(
            ArgumentConflictException ex) {
        return new ResponseEntity<>(
                new ErrorResponse(ex.getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Extracts the field and message from @{@link MethodArgumentNotValidException}
     *
     * @param ex MethodArgumentNotValidException
     * @return @{@link ErrorResponse} filled by @{@link MethodArgumentNotValidException}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ValidationError> validationErrors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            ValidationError validationError = new ValidationError(
                    fieldError.getField(), fieldError.getDefaultMessage());
            validationErrors.add(validationError);
        }
        return new ErrorResponse(VALIDATION_ERROR_MESSAGE, validationErrors);
    }
}
