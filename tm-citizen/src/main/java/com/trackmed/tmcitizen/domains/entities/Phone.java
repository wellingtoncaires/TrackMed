package com.trackmed.tmcitizen.domains.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "CIT_T_PHONE")
public class Phone implements Serializable {
    public static final long serialVersionUID=1L;

    @EmbeddedId
    private PhonePK id;


    @JoinColumn(name = "user_id", insertable = false, updatable = false, nullable = false)
    private Citizen user;

    @Column(name = "phone_number", insertable = false, updatable = false, nullable = false)
    private String phoneNumber;
}
