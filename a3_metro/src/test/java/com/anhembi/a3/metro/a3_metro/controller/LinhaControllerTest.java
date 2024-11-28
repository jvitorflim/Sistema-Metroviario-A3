package com.anhembi.a3.metro.a3_metro.controller;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.anhembi.a3.metro.a3_metro.model.AvisoUsuario;
import com.anhembi.a3.metro.a3_metro.service.AvisoUsuarioService;

public class LinhaControllerTest {

    @InjectMocks
    private LinhaController linhaController;

    @Mock
    private AvisoUsuarioService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarAvisoUsuario_Success() {
        AvisoUsuario avisoUsuario = new AvisoUsuario(); 
        avisoUsuario.setId(1); 
        when(service.create(avisoUsuario)).thenReturn(Optional.of(avisoUsuario));

        ResponseEntity<AvisoUsuario> response = linhaController.cadastrarAvisoUsuario(avisoUsuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(avisoUsuario, response.getBody());
    }

    @Test
    public void testCadastrarAvisoUsuario_BadRequest() {
        AvisoUsuario avisoUsuario = new AvisoUsuario(); 
        when(service.create(avisoUsuario)).thenReturn(Optional.empty());

        ResponseEntity<AvisoUsuario> response = linhaController.cadastrarAvisoUsuario(avisoUsuario);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}