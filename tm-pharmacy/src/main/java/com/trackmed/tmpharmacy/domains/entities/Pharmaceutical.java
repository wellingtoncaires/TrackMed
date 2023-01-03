package com.trackmed.tmpharmacy.domains.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "PHA_T_PHARMACEUTICAL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor@EqualsAndHashCode
public class Pharmaceutical implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pharmaceutical_id")
    private UUID id;

    @Column(name = "pharmacy_id", nullable = false)
    private Pharmacy pharmacy;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "register")
    private RegulatoryPharmaceuticalBody regulatoryBody;

}
