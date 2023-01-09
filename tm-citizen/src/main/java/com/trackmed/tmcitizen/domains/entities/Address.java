package com.trackmed.tmcitizen.domains.entities;

import com.trackmed.tmcitizen.domains.enums.Uf;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "CIT_T_ADDRESS")
@Data
public class Address implements Serializable {
    private static final long serialVersionUUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private UUID id;

    private String cep;

    private Integer numero;

    private String logradouro;

    private String complemento;

    private String bairro;

    private String localidade;

    @Enumerated(EnumType.STRING)
    private Uf uf;

    private Integer ibge;

    private Integer gia;

    private int ddd;

    private Integer siafi;
}
