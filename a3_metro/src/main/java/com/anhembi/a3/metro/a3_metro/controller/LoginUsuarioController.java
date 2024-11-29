package com.anhembi.a3.metro.a3_metro.controller;

import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("../login/login.html")
public class LoginUsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("../login/login.html")
    public ResponseEntity<Usuario> loginUsuario(@RequestBody Usuario usuario) {

        String emailUsuario = usuario.getEmail();
        String senhaUsuario = usuario.getSenha();

        if (!emailValido(emailUsuario)) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Usuario> usuarioOptional = service.findByEmail(emailUsuario);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!senhaUsuario.equals(usuarioOptional.get().getSenha())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("../web/telaColaborador/index.html")
    public ResponseEntity<Usuario> loginUsuarioTecnico(@RequestBody Usuario usuario) {

        String emailUsuario = usuario.getEmail();
        String senhaUsuario = usuario.getSenha();

        if (!emailValido(emailUsuario)) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Usuario> usuarioOptional = service.findByEmail(emailUsuario);

        if (usuarioOptional.get().isTecnico()) {
            if (usuarioOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
    
            if (!senhaUsuario.equals(usuarioOptional.get().getSenha())) {
                return ResponseEntity.badRequest().build();
            }
    
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public boolean emailValido(String emailUsuario) {
        boolean valido = false;
        if (emailUsuario != null) {
            // Expressão regular simples para validação de email
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            valido = Pattern.matches(emailRegex, emailUsuario);
        }
        return valido;
    }

}
