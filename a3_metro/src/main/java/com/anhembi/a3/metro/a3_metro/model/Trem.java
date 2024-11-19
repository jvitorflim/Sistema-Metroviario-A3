package com.anhembi.a3.metro.a3_metro.model;

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
@Table(name = "tb_trem")
public class Trem extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trem_pk")
    private int id;

    @Column(name = "identificacao", nullable = false)
    private String identificacao;

    @Column(name = "velocidade", nullable = false)
    private Double velocidade;

    @Column(name = "velocidade_max", nullable = false)
    private Double velocidadeMaxima;

    @Column(name = "qtd_ocupacao", nullable = false)
    private boolean qtdOcupacao;

    @Column(name = "ar_condicionado", nullable = false)
    private boolean arCondicionado;

    @ManyToOne
    @JoinColumn(name = "id_linha_fk", referencedColumnName="id_linha_pk")
    //@JsonIgnoreProperties("id_linha_pk")
    Linha linha;

}
