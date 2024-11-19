package com.anhembi.a3.metro.a3_metro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.service.UsuarioService;

public class CadastroUsuarioController {

    private Usuario usuario;

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> novoUsuario = service.create(usuario);

        if (novoUsuario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(novoUsuario.get(), HttpStatus.CREATED);
    }
}
