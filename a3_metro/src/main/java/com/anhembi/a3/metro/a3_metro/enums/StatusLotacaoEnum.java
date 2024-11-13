package com.anhembi.a3.metro.a3_metro.enums;

import lombok.Getter;

@Getter
public enum StatusLotacaoEnum {
    VAZIO(1, "Vazio"),
    POUCO_LOTADO(2, "Pouco Lotado"),
    LOTADO(3, "Lotado"),
    SUPERLOTADO(4, "Superlotado");

    private Integer codigo;
    private String descricao;

    StatusLotacaoEnum(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
