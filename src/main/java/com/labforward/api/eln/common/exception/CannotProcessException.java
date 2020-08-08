package com.labforward.api.eln.common.exception;

public class CannotProcessException extends RuntimeException {

    public static String MESSAGE = "Cannot process, required field(s) are missing";

    public CannotProcessException() {
        super(MESSAGE);
    }
}
