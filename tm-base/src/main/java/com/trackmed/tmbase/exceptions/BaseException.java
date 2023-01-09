package com.trackmed.tmbase.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserException extends RuntimeException implements Serializable {
    private static final long serialVErsionUID = 1L;


    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
