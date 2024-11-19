package com.anhembi.a3.metro.a3_metro.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.anhembi.a3.metro.a3_metro.model.AbstractEntity;

@Service
public abstract class AbstractService<E extends AbstractEntity> {

    public E create(E entidade) {
        entidade.setAtivo(true);
        entidade.setDataCriaçao(new Date());
        entidade.setDataModificacao(new Date());
        return entidade;
    }

    public E update(E entidade) {
        entidade.setDataModificacao(new Date());
        return entidade;
    }

    public E delete(E entidade) {
        entidade.setAtivo(false);
        entidade.setDataModificacao(new Date());
        return entidade;
    }
}
