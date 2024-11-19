package com.anhembi.a3.metro.a3_metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.service.UsuarioService;

public class CadastroUsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = service.create(usuario);

        if (novoUsuario == null) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
}
