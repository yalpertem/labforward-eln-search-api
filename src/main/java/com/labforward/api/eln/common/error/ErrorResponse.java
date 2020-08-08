package com.labforward.api.eln.common.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * General response to be returned from the API when an error occurs
 */
public class ErrorResponse {

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ValidationError> validationErrors;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, List<ValidationError> validationErrors) {
        this(message);
        this.validationErrors = validationErrors;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
}