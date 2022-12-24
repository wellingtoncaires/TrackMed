package com.trackmed.tmmock.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MockException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;


    public MockException(String message) {
        super(message);
    }

    public MockException(String message, Throwable cause) {
        super(message, cause);
    }
}
