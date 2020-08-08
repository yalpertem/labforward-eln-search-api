package com.labforward.api.eln.common.exception;

public class ArgumentConflictException extends RuntimeException {

    public static String MESSAGE = "Conflict in submitted request";

    public ArgumentConflictException() {
        super(MESSAGE);
    }
}

