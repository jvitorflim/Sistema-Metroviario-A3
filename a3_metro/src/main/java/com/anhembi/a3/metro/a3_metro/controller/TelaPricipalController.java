package com.anhembi.a3.metro.a3_metro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhembi.a3.metro.a3_metro.model.Noticia;
import com.anhembi.a3.metro.a3_metro.service.NoticiaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/tela_pricipal")
public class TelaPricipalController {

    @Autowired
    private NoticiaService noticiaService ;

    @GetMapping
    public ResponseEntity<List<Noticia>> buscarNoticias() {
        List<Noticia> novasNoticias = noticiaService.findAll();

        if (novasNoticias.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<>(novasNoticias, HttpStatus.CREATED);
    }
}
