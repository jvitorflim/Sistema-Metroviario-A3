/*package com.anhembi.a3.metro.a3_metro.controller;

import com.anhembi.a3.metro.a3_metro.enums.TipoAvisoEnum;
import com.anhembi.a3.metro.a3_metro.model.Noticia;
import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.service.NoticiaService;
import com.anhembi.a3.metro.a3_metro.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TelaPricipalController.class)
public class TelaPrincipalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticiaService noticiaService;

    @Test
    public void buscarNoticias_retornaNoticias_quandoExistemNoticias() throws Exception {
        List<Noticia> noticias = Arrays.asList(
            new Noticia(1, "Atraso na linha", TipoAvisoEnum.ATRASO, null, null),
            new Noticia(2, "Falha técnica", TipoAvisoEnum.FALHA_TECNICA, null, null)
        );
        Mockito.when(noticiaService.findAll()).thenReturn(noticias);

        mockMvc.perform(get("/tela_pricipal"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].descricao").value("Atraso na linha"))
                .andExpect(jsonPath("$[0].tipoAviso").value("ATRASO"))
                .andExpect(jsonPath("$[1].descricao").value("Falha técnica"))
                .andExpect(jsonPath("$[1].tipoAviso").value("FALHA_TECNICA"));
    }

    @Test
    public void buscarNoticias_retornaBadRequest_quandoNaoExistemNoticias() throws Exception {
        Mockito.when(noticiaService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/tela_pricipal"))
                .andExpect(status().isBadRequest());
    }
}*/