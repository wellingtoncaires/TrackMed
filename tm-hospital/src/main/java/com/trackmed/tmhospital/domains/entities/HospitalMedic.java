package com.trackmed.tmhospital.domains.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "HOS_T_HOSPITAL_MEDIC")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HospitalMedic implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    private UUID id;
}
