package com.anhembi.a3.metro.a3_metro.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.anhembi.a3.metro.a3_metro.model.AbstractEntity;

@Service
public abstract class AbstractService<E extends AbstractEntity> {

    public Optional<E> create(E entidade) {
        if (entidade == null) {
            return Optional.empty(); 
        }

        entidade.setAtivo(true);
        entidade.setDataCriacao(new Date());
        entidade.setDataModificacao(new Date());
        return Optional.of(entidade);
    }

    public Optional<E> update(E entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("A entidade não pode ser nula");
        }
    
        entidade.setDataModificacao(new Date());
        return Optional.of(entidade);
    }
    
    public Optional<E> delete(E entidade) {
        if (entidade == null) {
            throw new IllegalArgumentException("A entidade não pode ser nula");
        }

        entidade.setAtivo(false);
        entidade.setDataModificacao(new Date());
        return Optional.of(entidade);
    }
}
