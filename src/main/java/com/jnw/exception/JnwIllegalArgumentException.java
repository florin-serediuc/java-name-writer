package com.jnw.exception;

import lombok.extern.java.Log;

@Log
public class JnwIllegalArgumentException extends IllegalArgumentException {

    public JnwIllegalArgumentException(String errorMessage) {
        super(errorMessage);
        log.info(String.format("JNW Info: %s", errorMessage));
    }
}
