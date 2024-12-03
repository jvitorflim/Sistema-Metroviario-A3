package com.anhembi.a3.metro.a3_metro.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.repository.UsuarioRepo;

@Service
public class UsuarioService extends AbstractService<Usuario> {

    @Autowired
    private UsuarioRepo repo;

    public Optional<Usuario> create(Usuario usuario) {
        try {
            super.create(usuario);
            if (usuario.getId() == 0) {
                return Optional.of(repo.save(usuario));
            }
            return Optional.empty();
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public Optional<Usuario> update(Usuario usuario) {
        try {
            super.update(usuario);
            if (usuario.getId() > 0) {
                Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
                if (usuarioOptional.isPresent()) {
                    return Optional.of(repo.save(usuario));
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public Optional<Usuario> updatePartial(Usuario usuario) {
        try {
            if (usuario.getId() > 0) {
                Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
                if (!usuarioOptional.isEmpty()) {
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
            }
            return Optional.empty();
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public Optional<Usuario> findById(int id) {
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

    public List<Usuario> findAll() {
        try {
            return (List<Usuario>) repo.findAll();
        } catch (Exception e) {
            e.getMessage();
            return Collections.emptyList();
        }
    }

    public Optional<Usuario> findByEmail(String emailUsuario) {
        try {
            if (emailUsuario.isEmpty()) {
                return Optional.empty();
            }
            return repo.findByEmail(emailUsuario);
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }

    public Optional<Usuario> delete(Usuario usuario) {
        try {
            if (usuario == null) {
                return Optional.empty();
            }
            Optional<Usuario> usuarioOptional = repo.findById(usuario.getId());
            if (!usuarioOptional.isPresent()) {
                return Optional.empty();
            }

            super.delete(usuario);
            updatePartial(usuario);
            return Optional.of(usuario);
        } catch (Exception e) {
            e.getMessage();
            return Optional.empty();
        }
    }
}
