package com.anhembi.a3.metro.a3_metro.model;

import com.anhembi.a3.metro.a3_metro.enums.TipoAvisoEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "tb_aviso_usuario")
public class AvisoUsuario extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aviso_usuario_pk")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_aviso", nullable = false, length = 20)
    private TipoAvisoEnum tipoAviso;

    @ManyToOne
    @JoinColumn(name = "id_usuario_fk", referencedColumnName="id_usuario_pk", nullable = true)
    @JsonIgnoreProperties("id_usuario_pk")
    Usuario usuario;
}
