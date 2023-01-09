package com.trackmed.tmbase.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseException extends RuntimeException implements Serializable {
    private static final long serialVErsionUID = 1L;


    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
