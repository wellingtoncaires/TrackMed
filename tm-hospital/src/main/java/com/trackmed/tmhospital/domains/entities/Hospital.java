package com.trackmed.tmhospital.domains.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "HOS_T_HOSPITAL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hospital implements Serializable {
    public static final long serialVersionUID=1L;

    @Id
    @Column(name = "hospital_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String webSite;

    private boolean operationalLicense;

    public Hospital(String name, String webSite, boolean operationalLicense) {
        this.name = name;
        this.webSite = webSite;
        this.operationalLicense = operationalLicense;
    }
}
