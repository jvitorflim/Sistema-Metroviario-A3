package com.anhembi.a3.metro.a3_metro.enums;

import lombok.Getter;

@Getter
public enum TipoAvisoEnum {
    ATRASO(1, "Atraso"),
    ACIDENTE(2, "Acidente"),
    SUPERLOTACAO(3, "Superlotação"),
    VAZIO(4, "Vazio"),
    OBSTRUCAO(5, "Obstrução na via"),
    FALTA_ENERGIA(6,"Falta de energia"),
    FALHA_TECNICA(7, "Falha técnica"),
    QUEBRADO(7, "Quebrado");

    private Integer codigo;
    private String nome;

    TipoAvisoEnum(Integer codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    } 

}
