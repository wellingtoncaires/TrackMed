package com.trackmed.tmmock.domains.entities;

import com.trackmed.tmmock.domains.enums.MedicineType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "MOCK_T_MEDICINE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Medicine implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "medicine_id")
    private UUID id;

    @Column(nullable = false)
    private String activePrinciple;

    @Column(nullable = false)
    private MedicineType medicineType;

    @Column(nullable = false)
    private String commercialName;

    @Column(nullable = false)
    private Double posology;

    @Column(nullable = false)
    private String company;

    private boolean approved;
}
