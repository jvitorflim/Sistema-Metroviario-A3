package com.anhembi.a3.metro.a3_metro.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhembi.a3.metro.a3_metro.model.AvisoUsuario;
import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.repository.AvisoUsuarioRepo;

@Service
public class AvisoUsuarioService extends AbstractService<AvisoUsuario> {

    @Autowired
    private AvisoUsuarioRepo repo;

    public Optional<AvisoUsuario> create(AvisoUsuario avisoUsuario) {
        try {
            super.create(avisoUsuario);
            if (avisoUsuario.getId() == 0) {
                return Optional.empty();
            }
            return Optional.of(repo.save(avisoUsuario));
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public Optional<AvisoUsuario> findById(int id) {
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

    public List<AvisoUsuario> findAll() {
        try {
            return (List<AvisoUsuario>) repo.findAll();
        } catch (Exception e) {
            e.getMessage();
            return Collections.emptyList();
        }
    }

    public List<AvisoUsuario> findAllByUsuario(Usuario usuario) {
        try {
            List<AvisoUsuario> listAvisoUsuario = new ArrayList<>();

            if (usuario != null) {
                listAvisoUsuario = repo.findAllByUsuario(usuario);
            }
            return listAvisoUsuario;
        } catch (Exception e) {
            e.getMessage();
            return Collections.emptyList();
        }
    }

    public List<AvisoUsuario> findAllByDataCriacao(Date data) {
        try {
            List<AvisoUsuario> listAvisoUsuario = new ArrayList<>();
            if (data != null) {
                listAvisoUsuario = repo.findAllByDataCriacao(data);
            }
            return listAvisoUsuario;
        } catch (Exception e) {
            e.getMessage();
            return Collections.emptyList();
        }
    }
}
