package com.anhembi.a3.metro.a3_metro.controller;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CadastroUsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private CadastroUsuarioController cadastroUsuarioController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cadastroUsuarioController).build();
    }

    @Test
    void testCadastrarUsuario_Sucesso() throws Exception {
        // Dados do usuário para o teste
        Usuario usuario = new Usuario();
        usuario.setNome("Bruno");
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("senha123");
        usuario.setTecnico(false);

        // Simulando a criação do usuário com sucesso
        when(usuarioService.create(any(Usuario.class))).thenReturn(Optional.of(usuario));

        // Realizando a requisição POST
        mockMvc.perform(post("/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isCreated()) // Espera um status 201 Created
                .andExpect(jsonPath("$.nome").value("Bruno")) // Verifica o nome do usuário
                .andExpect(jsonPath("$.email").value("usuario@teste.com")); // Verifica o email
    }

    @Test
    void testCadastrarUsuario_Falha() throws Exception {
        // Dados do usuário para o teste
        Usuario usuario = new Usuario();
        usuario.setNome("Bruno");
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("senha123");
        usuario.setTecnico(false);

        // Simulando uma falha na criação do usuário
        when(usuarioService.create(any(Usuario.class))).thenReturn(Optional.empty());

        // Realizando a requisição POST
        mockMvc.perform(post("/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuario)))
                .andExpect(status().isBadRequest()); // Espera um status 400 Bad Request
    }
}
