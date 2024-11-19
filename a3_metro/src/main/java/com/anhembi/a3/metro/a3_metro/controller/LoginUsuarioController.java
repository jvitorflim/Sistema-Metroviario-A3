package com.anhembi.a3.metro.a3_metro.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.service.UsuarioService;

public class LoginUsuarioController {

    private Usuario usuario;

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> loginUsuario(@RequestBody Usuario usuario) {

        String emailUsuario = usuario.getEmail();
        String senhaUsuario = usuario.getSenha();

        if (!emailValido(emailUsuario)) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Usuario> usuarioOptional = service.findByEmail(emailUsuario);

        if (!senhaUsuario.equals(usuarioOptional.get().getSenha())) {
            return ResponseEntity.badRequest().build();
        }

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Usuario> loginUsuarioTecnico(@RequestBody Usuario usuario) {

        String emailUsuario = usuario.getEmail();
        String senhaUsuario = usuario.getSenha();

        if (!emailValido(emailUsuario)) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Usuario> usuarioOptional = service.findByEmail(emailUsuario);

        if (usuarioOptional.get().isTecnico()) {
            if (!senhaUsuario.equals(usuarioOptional.get().getSenha())) {
                return ResponseEntity.badRequest().build();
            }

            if (usuarioOptional.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public boolean emailValido(String emailUsuario) {
        // Expressão regular simples para validação de email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, emailUsuario);
    }

}
