package com.anhembi.a3.metro.a3_metro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_estacao")
public class Estacao extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estacao_pk")
    private int id;

    @Column(name = "nm_estacao", nullable = false)
    private String nome;

    @Column(name = "cod_estacao", nullable = false)
    private String codigo;

    @Column(name = "ordem", nullable = false)
    private int ordem;

    @Column(name = "localizacao", nullable = false)
    private String localizacao;

    @ManyToOne
    @JoinColumn(name = "id_linha_fk", referencedColumnName="id_linha_pk")
    @JsonIgnoreProperties("id_linha_pk")
    Linha linha;
}
