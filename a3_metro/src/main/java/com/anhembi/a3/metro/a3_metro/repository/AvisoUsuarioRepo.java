package com.anhembi.a3.metro.a3_metro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anhembi.a3.metro.a3_metro.model.AvisoUsuario;
import com.anhembi.a3.metro.a3_metro.model.Usuario;

public interface AvisoUsuarioRepo extends CrudRepository<AvisoUsuario, Integer> {
    List<AvisoUsuario> findAllByUsuario(Usuario usuario);

    List<AvisoUsuario> findAllByDataCriacao(Date dataCriaçao);
}
