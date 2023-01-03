package com.trackmed.tmpharmacy.domains.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "PHA_T_REGULATORY_PH_BODY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RegulatoryPharmaceuticalBody implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "regulatory_ph_body_id")
    private UUID id;

    private String pharmaceuticalName;

    private String pharmaceuticalLastName;

    private String pharmaceuticalCpf;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    private boolean enabled;
}
