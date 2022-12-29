package com.trackmed.tmcitizen.domains.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "CIT_T_TREATMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Treatment implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "treatment_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @Column(name = "medic_id", nullable = false)
    private UUID medic;

    @Column(name = "medicine_id", nullable = false)
    private UUID medicine;

    @Column(name = "appointment_id", nullable = false)
    private UUID appointment;

    @Column(nullable = false)
    private int medicineQuantity;

    @Column(precision = 3)
    private Double monthlyQuantity;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date prescriptionExpirationDate;

    @Column(nullable = false)
    private int medicineBalance;

    @Column(length = 500)
    private String note;
}
