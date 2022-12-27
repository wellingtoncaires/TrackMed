package com.trackmed.tmhospital.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class HospitalException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;


    public HospitalException(String message) {
        super(message);
    }

    public HospitalException(String message, Throwable cause) {
        super(message, cause);
    }
}
