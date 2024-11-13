package com.anhembi.a3.metro.a3_metro.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    @Column(name = "dt_criacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCriaçao;

    @Column(name = "dt_modificacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataModificacao;

    @Column(name = "ativo", nullable = false, columnDefinition = "boolean default true")
    private boolean ativo;

}
