package com.anhembi.a3.metro.a3_metro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.repository.UsuarioRepo;

@Service
public class UsuarioService extends AbstractService<Usuario>{

    @Autowired
    private UsuarioRepo repo;

    public Usuario create(Usuario usuario) {
        try {
            super.create(usuario);
            if (usuario.getId() != 0) {
                return null;
            }
        return repo.save(usuario);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Usuario update(Usuario usuario) {
        try {
            super.update(usuario);
            if (usuario.getId() <= 0) {
                return null;
            }
            Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
            if (!usuarioOptional.isPresent()) {
                return null;
            }
            return repo.save(usuario);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    //CONTINUAR!

    public Optional<Usuario> updatePartial(Usuario usuario) {
        if (usuario.getId() <= 0) {
            return Optional.empty();
        }
        Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
        if (!usuarioOptional.isPresent()) {
            return Optional.empty();
        }

        Usuario UsuarioFound = usuarioOptional.get();

        if (usuario.getNome() != null &&
                !usuario.getNome().isEmpty()) {
            UsuarioFound.setNome(usuario.getNome());
        }
        if (usuario.getSenha().isEmpty()) {
            UsuarioFound.setSenha(usuario.getSenha());
        }
        return Optional.of(repo.save(UsuarioFound));
    }

    public Optional<Usuario> findById(int id) {
        if (id <= 0) {
            return Optional.empty();
        }
        return repo.findById(id);
    }

    public List<Usuario> findAll() {
        return (List<Usuario>) repo.findAll();
    }

    public boolean delete(int cod) {
        if (cod <= 0) {
            return false;
        }
        Optional<Usuario> usuarioOptional = repo.findById(cod);
        if (!usuarioOptional.isPresent()) {
            return false;
        }

        repo.deleteById(cod);
        return true;
    }
}
