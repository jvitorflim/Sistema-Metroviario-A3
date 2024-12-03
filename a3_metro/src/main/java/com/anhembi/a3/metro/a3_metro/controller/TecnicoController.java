package com.anhembi.a3.metro.a3_metro.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhembi.a3.metro.a3_metro.model.AvisoUsuario;
import com.anhembi.a3.metro.a3_metro.model.Noticia;
import com.anhembi.a3.metro.a3_metro.service.AvisoUsuarioService;
import com.anhembi.a3.metro.a3_metro.service.NoticiaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/tecnico")
public class TecnicoController {

    @Autowired
    private AvisoUsuarioService serviceAvisoUsuario;

    @Autowired
    private NoticiaService noticiaService;


    @PostMapping
    public ResponseEntity<Noticia> cadastrarNoticia(@RequestBody Noticia noticia) {
        Optional<Noticia> novoAvisoUsuario = noticiaService.create(noticia);

        if (novoAvisoUsuario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(novoAvisoUsuario.get(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AvisoUsuario>> buscarAvisoUsuarioDia() {
        List<AvisoUsuario> novosAvisoUsuario = serviceAvisoUsuario.findAllByDataCriacao(new Date());

        if (novosAvisoUsuario.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(novosAvisoUsuario, HttpStatus.CREATED);
    }
}
