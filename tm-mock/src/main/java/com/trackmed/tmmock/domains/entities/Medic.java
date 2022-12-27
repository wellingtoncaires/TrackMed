package com.trackmed.tmmock.domains.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "MOCK_T_MEDIC")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Medic implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "medic_id")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String cpf;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RegulatoryMedicBody> listRegulatoryMedicBody = new ArrayList<>();;

//    public Medic(String name, String lastName, Date birthDate, String cpf) {
//        this.name = name;
//        this.lastName = lastName;
//        this.birthDate = birthDate;
//        this.cpf = cpf;
//        this.listRegulatoryMedicBody = new ArrayList<>();
//    }
}
