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

    public Optional<Usuario> create(Usuario usuario) {
        try {
            super.create(usuario);
            if (usuario.getId() != 0) {
                return Optional.empty();
            }
        usuario.setTecnico(false);
        return  Optional.of(repo.save(usuario));
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public Optional<Usuario> update(Usuario usuario) {
        try {
            super.update(usuario);
            if (usuario.getId() <= 0) {
                return Optional.empty();
            }
            Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
            if (!usuarioOptional.isPresent()) {
                return Optional.empty();
            }
            return Optional.of(repo.save(usuario));
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public Optional<Usuario> updatePartial(Usuario usuario) {
        if (usuario.getId() <= 0) {
            return Optional.empty();
        }
        Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
        if (usuarioOptional.isEmpty()) {
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
        if (!usuario.isAtivo()) {
            UsuarioFound.setAtivo(usuario.isAtivo());
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

    public Optional<Usuario> findByEmail(String emailUsuario) {
        if (emailUsuario.isEmpty()) {
            return Optional.empty();
        }
        List<Usuario> usuarios = findAll();

        return usuarios.stream()
                   .filter(usuario -> usuario.getEmail().equalsIgnoreCase(emailUsuario))
                   .findFirst();
    }

    public Optional<Usuario> delete(Usuario usuario) {
        if (usuario != null) {
            return Optional.empty();
        }
        Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
        if (!usuarioOptional.isPresent()) {
            return Optional.empty();
        }

        super.delete(usuario);
        updatePartial(usuario);
        return Optional.of(usuario);
    }
}
