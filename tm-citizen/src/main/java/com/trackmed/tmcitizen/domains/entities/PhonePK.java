package com.trackmed.tmcitizen.domains.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PhonePK implements Serializable {
    public static final long serialVersionUID=1L;

    @Column(name = "user_id", nullable = false)
    private UUID user;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
}
