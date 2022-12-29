package com.trackmed.tmcitizen.exceptions;

import lombok.Getter;

import java.io.Serializable;

public class CommunicationMicroServiceException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    private Integer status;

    public CommunicationMicroServiceException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
