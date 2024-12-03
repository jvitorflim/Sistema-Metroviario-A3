package com.anhembi.a3.metro.a3_metro.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginUsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> loginUsuario(@RequestBody Usuario usuario) {
        return autenticarUsuario(usuario, false);
    }

    @PostMapping("/tecnico")
    public ResponseEntity<Usuario> loginUsuarioTecnico(@RequestBody Usuario usuario) {
        return autenticarUsuario(usuario, true);
    }

    private ResponseEntity<Usuario> autenticarUsuario(Usuario usuario, boolean isTecnico) {
        String emailUsuario = usuario.getEmail();
        String senhaUsuario = usuario.getSenha();

        if (!emailValido(emailUsuario)) {
            return ResponseEntity.badRequest().body(null);
        }

        Optional<Usuario> usuarioOptional = service.findByEmail(emailUsuario);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuarioEncontrado = usuarioOptional.get();

        if (isTecnico && !usuarioEncontrado.isTecnico()) {
            return ResponseEntity.status(403).build(); // Proibido
        }

        if (!senhaUsuario.equals(usuarioEncontrado.getSenha())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(usuarioEncontrado);
    }

    private boolean emailValido(String emailUsuario) {
        if (emailUsuario == null) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, emailUsuario);
    }
}