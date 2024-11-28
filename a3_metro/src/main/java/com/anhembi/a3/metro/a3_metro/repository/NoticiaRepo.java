package com.anhembi.a3.metro.a3_metro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.anhembi.a3.metro.a3_metro.model.Noticia;
import com.anhembi.a3.metro.a3_metro.model.Usuario;

public interface NoticiaRepo extends CrudRepository<Noticia, Integer> {
    List<Noticia> findAllByUsuario(Usuario usuario);

    List<Noticia> findAllByDataCriacao(Date dataCriaçao);
}
