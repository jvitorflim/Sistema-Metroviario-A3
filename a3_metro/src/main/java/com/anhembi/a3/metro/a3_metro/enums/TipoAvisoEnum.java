package com.anhembi.a3.metro.a3_metro.enums;

import lombok.Getter;

@Getter
public enum TipoAvisoEnum {
    ATRASO(1, "Atraso"),
    ACIDENTE(2, "Acidente"),
    SUPERLOTACAO(3, "Superlotação"),
    OBSTRUCAO(4, "Obstrução na via"),
    FALTA_ENERGIA(5,"Falta de energia"),
    FALHA_TECNICA(6, "Falha técnica");

    private Integer codigo;
    private String nome;

    TipoAvisoEnum(Integer codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    } 

}
