package com.labforward.api.eln.common.exception;

public class BadRequestException extends RuntimeException {

    public static String MESSAGE = "Bad request";

    public BadRequestException() {
        super(MESSAGE);
    }
}