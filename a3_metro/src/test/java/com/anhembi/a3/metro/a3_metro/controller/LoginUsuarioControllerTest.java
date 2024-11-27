/*package com.anhembi.a3.metro.a3_metro.controller;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class LoginUsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private LoginUsuarioController loginUsuarioController;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginUsuarioController).build();

        // Criação de um usuário para os testes
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Bruno");
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("senha123");
        usuario.setTecnico(true);
        usuario.setAtivo(true);
    }

    @Test
    void testLoginUsuario_Sucesso() throws Exception {
        // Mockando o serviço para retornar o usuário
        when(usuarioService.findByEmail("usuario@teste.com")).thenReturn(Optional.of(usuario));

        // Criando o JSON para a requisição
        String usuarioJson = "{\"email\":\"usuario@teste.com\", \"senha\":\"senha123\"}";

        // Executando o teste da requisição GET /login/{usuario}
        mockMvc.perform(get("/login/{usuario}", "usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isOk()) // Espera status 200 OK
                .andExpect(jsonPath("$.email").value("usuario@teste.com")) // Verifica se o email retornado é o esperado
                .andExpect(jsonPath("$.nome").value("Bruno")); // Verifica se o nome retornado é o esperado
    }

    @Test
    void testLoginUsuario_EmailInvalido() throws Exception {
        // Criando o JSON para a requisição com um email inválido
        String usuarioJson = "{\"email\":\"usuario.com\", \"senha\":\"senha123\"}";

        // Executando o teste da requisição GET /login/{usuario}
        mockMvc.perform(get("/login/{usuario}", "usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isBadRequest()); // Espera status 400 Bad Request
    }

    @Test
    void testLoginUsuario_UsuarioNaoEncontrado() throws Exception {
        // Mockando o serviço para não encontrar o usuário
        when(usuarioService.findByEmail("usuario@teste.com")).thenReturn(Optional.empty());

        // Criando o JSON para a requisição
        String usuarioJson = "{\"email\":\"usuario@teste.com\", \"senha\":\"senha123\"}";

        // Executando o teste da requisição GET /login/{usuario}
        mockMvc.perform(get("/login/{usuario}", "usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isNotFound()); // Espera status 404 Not Found
    }

    @Test
    void testLoginUsuario_SenhaIncorreta() throws Exception {
        // Mockando o serviço para retornar o usuário
        when(usuarioService.findByEmail("usuario@teste.com")).thenReturn(Optional.of(usuario));

        // Criando o JSON para a requisição com senha incorreta
        String usuarioJson = "{\"email\":\"usuario@teste.com\", \"senha\":\"senhaErrada\"}";

        // Executando o teste da requisição GET /login/{usuario}
        mockMvc.perform(get("/login/{usuario}", "usuario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isBadRequest()); // Espera status 400 Bad Request
    }

    @Test
    void testLoginUsuarioTecnico_Sucesso() throws Exception {
        // Mockando o serviço para retornar o usuário
        when(usuarioService.findByEmail("usuario@teste.com")).thenReturn(Optional.of(usuario));

        // Criando o JSON para a requisição
        String usuarioJson = "{\"email\":\"usuario@teste.com\", \"senha\":\"senha123\"}";

        // Executando o teste da requisição GET /login/{usuario_tecnico}
        mockMvc.perform(get("/login/{usuario_tecnico}", "usuario_tecnico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isOk()) // Espera status 200 OK
                .andExpect(jsonPath("$.email").value("usuario@teste.com")) // Verifica se o email retornado é o esperado
                .andExpect(jsonPath("$.nome").value("Bruno")); // Verifica se o nome retornado é o esperado
    }

    @Test
    void testLoginUsuarioTecnico_UsuarioNaoTecnico() throws Exception {
        // Alterando o usuário para não ser técnico
        usuario.setTecnico(false);

        // Mockando o serviço para retornar o usuário
        when(usuarioService.findByEmail("usuario@teste.com")).thenReturn(Optional.of(usuario));

        // Criando o JSON para a requisição
        String usuarioJson = "{\"email\":\"usuario@teste.com\", \"senha\":\"senha123\"}";

        // Executando o teste da requisição GET /login/{usuario_tecnico}
        mockMvc.perform(get("/login/{usuario_tecnico}", "usuario_tecnico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isBadRequest()); // Espera status 400 Bad Request, pois o usuário não é técnico
    }

    @Test
    void testLoginUsuarioTecnico_SenhaIncorreta() throws Exception {
        // Mockando o serviço para retornar o usuário
        when(usuarioService.findByEmail("usuario@teste.com")).thenReturn(Optional.of(usuario));

        // Criando o JSON para a requisição com senha incorreta
        String usuarioJson = "{\"email\":\"usuario@teste.com\", \"senha\":\"senhaErrada\"}";

        // Executando o teste da requisição GET /login/{usuario_tecnico}
        mockMvc.perform(get("/login/{usuario_tecnico}", "usuario_tecnico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioJson))
                .andExpect(status().isBadRequest()); // Espera status 400 Bad Request, pois a senha está errada
    }
}*/