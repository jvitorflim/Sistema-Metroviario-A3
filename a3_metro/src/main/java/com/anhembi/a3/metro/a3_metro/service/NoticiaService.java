package com.anhembi.a3.metro.a3_metro.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhembi.a3.metro.a3_metro.model.Noticia;
import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.repository.NoticiaRepo;

@Service
public class NoticiaService extends AbstractService<Noticia> {

    @Autowired
    private NoticiaRepo repo;

    public Optional<Noticia> create(Noticia noticia) {
        try {
            super.create(noticia);
            if (noticia.getId() == 0) {
                return Optional.empty();
            }
            return Optional.of(repo.save(noticia));
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public Optional<Noticia> findById(int id) {
        try {
            if (id <= 0) {
                return Optional.empty();
            }
            return repo.findById(id);
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public List<Noticia> findAll() {
        try {
            return (List<Noticia>) repo.findAll();
        } catch (Exception e) {
            e.getMessage();
            return Collections.emptyList();
        }
    }

    public List<Noticia> findAllByUsuario(Usuario usuario) {
        try {
            List<Noticia> listNoticias = new ArrayList<>();

            if (usuario != null) {
                listNoticias = repo.findAllByUsuario(usuario);
            }
            return listNoticias;
        } catch (Exception e) {
            e.getMessage();
            return Collections.emptyList();
        }
    }

    public List<Noticia> findAllByDataCriacao(Date data) {
        try {
            List<Noticia> listNoticias = new ArrayList<>();
            if (data != null) {
                listNoticias = repo.findAllByDataCriacao(data);
            }
            return listNoticias;
        } catch (Exception e) {
            e.getMessage();
            return Collections.emptyList();
        }
    }
}
