package com.labforward.api.eln.common.exception;

public class NotFoundException extends RuntimeException {

    public static String MESSAGE = "Resource not found";

    public NotFoundException() {
        super(MESSAGE);
    }
}