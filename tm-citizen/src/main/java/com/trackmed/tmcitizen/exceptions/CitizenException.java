package com.trackmed.tmcitizen.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CitizenException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;


    public CitizenException(String message) {
        super(message);
    }

    public CitizenException(String message, Throwable cause) {
        super(message, cause);
    }
}
