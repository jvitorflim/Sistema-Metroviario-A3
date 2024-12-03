package com.anhembi.a3.metro.a3_metro.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.anhembi.a3.metro.a3_metro.model.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
