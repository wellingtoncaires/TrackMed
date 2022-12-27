package com.trackmed.tmpharmacy.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PharmacyException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;


    public PharmacyException(String message) {
        super(message);
    }

    public PharmacyException(String message, Throwable cause) {
        super(message, cause);
    }
}
