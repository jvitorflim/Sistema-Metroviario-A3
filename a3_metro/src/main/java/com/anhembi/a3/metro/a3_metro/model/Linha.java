package com.anhembi.a3.metro.a3_metro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_linha")
public class Linha extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linha_pk")
    private int id;

    @Column(name = "nm_linha", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

}
