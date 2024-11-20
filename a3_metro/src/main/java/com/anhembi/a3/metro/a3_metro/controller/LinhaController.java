package com.anhembi.a3.metro.a3_metro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhembi.a3.metro.a3_metro.model.AvisoUsuario;
import com.anhembi.a3.metro.a3_metro.service.AvisoUsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/linha")
public class LinhaController {

    @Autowired
    private AvisoUsuarioService service;

    @PostMapping
    public ResponseEntity<AvisoUsuario> cadastrarAvisoUsuario(@RequestBody AvisoUsuario avisoUsuario) {
        Optional<AvisoUsuario> novoAvisoUsuario = service.create(avisoUsuario);

        if (novoAvisoUsuario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(novoAvisoUsuario.get(), HttpStatus.CREATED);
    }
}
