package com.anhembi.a3.metro.a3_metro.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.repository.UsuarioRepo;

@SpringBootTest
class UsuarioServiceTest {

    @Mock
    private UsuarioRepo usuarioRepo;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioTecnico;
    private Usuario usuarioNaoTecnico;

    @BeforeEach
    void setUp() {
        // Criando usuário técnico (tecnico = true)
        usuarioTecnico = new Usuario();
        usuarioTecnico.setId(1);
        usuarioTecnico.setNome("Bruno");
        usuarioTecnico.setEmail("usuario@teste.com");
        usuarioTecnico.setSenha("senha123");
        usuarioTecnico.setTecnico(true);

        // Criando usuário não técnico (tecnico = false)
        usuarioNaoTecnico = new Usuario();
        usuarioNaoTecnico.setId(2);
        usuarioNaoTecnico.setNome("Carlos");
        usuarioNaoTecnico.setEmail("carlos@teste.com");
        usuarioNaoTecnico.setSenha("senha123");
        usuarioNaoTecnico.setTecnico(false);
    }

    // Testes para verificar o comportamento do UsuarioService com usuários técnicos
    // e não técnicos.

    @Test
    void testLoginUsuarioTecnico_UsuarioTecnico() {
        // Mockando o retorno do repositório para um usuário técnico
        when(usuarioRepo.findByEmail("usuario@teste.com")).thenReturn(Optional.of(usuarioTecnico));

        // Simulando a lógica de login para técnico
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail("usuario@teste.com");

        assertTrue(usuarioOptional.isPresent());
        assertTrue(usuarioOptional.get().isTecnico()); // Verificando que é um técnico
    }

    @Test
    void testLoginUsuarioTecnico_UsuarioNaoTecnico() {
        // Mockando o retorno do repositório para um usuário não técnico
        when(usuarioRepo.findByEmail("carlos@teste.com")).thenReturn(Optional.of(usuarioNaoTecnico));

        // Simulando a lógica de login para técnico
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail("carlos@teste.com");

        assertTrue(usuarioOptional.isPresent());
        assertFalse(usuarioOptional.get().isTecnico()); // Verificando que não é um técnico
    }

    @Test
    void testLoginUsuarioTecnico_InvalidEmail() {
        // Mockando o retorno do repositório para um email inválido
        when(usuarioRepo.findByEmail("usuario@teste.com")).thenReturn(Optional.empty());

        // Simulando a lógica de login para técnico com email não encontrado
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail("usuario@teste.com");

        assertTrue(usuarioOptional.isEmpty()); // Verificando que não foi encontrado
    }

    // Testes para verificar o comportamento do UsuarioService em casos gerais de
    // CRUD.

    @Test
    void testCreateUsuario_Success() {
        // Mockando o retorno do repositório para um usuário válido
        when(usuarioRepo.save(usuarioTecnico)).thenReturn(usuarioTecnico);

        Optional<Usuario> usuarioOptional = usuarioService.create(usuarioTecnico);

        assertTrue(usuarioOptional.isPresent());
        assertEquals("Bruno", usuarioOptional.get().getNome());
        assertTrue(usuarioOptional.get().isTecnico()); // Verificando que é um técnico
    }

    @Test
    void testCreateUsuario_Fail() {
        // Simulando falha no processo de criação
        when(usuarioRepo.save(usuarioNaoTecnico)).thenThrow(new RuntimeException("Erro ao salvar usuário"));

        Optional<Usuario> usuarioOptional = usuarioService.create(usuarioNaoTecnico);

        assertTrue(usuarioOptional.isEmpty());
    }

    @Test
    void testUpdateUsuario_Success() {
        // Mockando o retorno do repositório para um usuário já existente
        when(usuarioRepo.findById(1)).thenReturn(Optional.of(usuarioTecnico));
        when(usuarioRepo.save(usuarioTecnico)).thenReturn(usuarioTecnico);

        Optional<Usuario> usuarioOptional = usuarioService.update(usuarioTecnico);

        assertTrue(usuarioOptional.isPresent());
        assertEquals("Bruno", usuarioOptional.get().getNome());
    }

    @Test
    void testUpdateUsuario_Fail() {
        // Simulando falha no processo de atualização (usuário não encontrado)
        when(usuarioRepo.findById(99)).thenReturn(Optional.empty());

        Optional<Usuario> usuarioOptional = usuarioService.update(usuarioTecnico);

        assertTrue(usuarioOptional.isEmpty());
    }

    @Test
    void testDeleteUsuario_Success() {
        // Mockando o retorno do repositório para um usuário válido
        when(usuarioRepo.findById(1)).thenReturn(Optional.of(usuarioTecnico));

        Optional<Usuario> usuarioOptional = usuarioService.delete(usuarioTecnico);

        assertTrue(usuarioOptional.isPresent());
        assertFalse(usuarioOptional.get().isAtivo()); // Verificando que o usuário foi marcado como inativo
    }

    @Test
    void testDeleteUsuario_Fail() {
        // Simulando falha no processo de exclusão (usuário não encontrado)
        when(usuarioRepo.findById(99)).thenReturn(Optional.empty());

        Optional<Usuario> usuarioOptional = usuarioService.delete(usuarioTecnico);

        assertTrue(usuarioOptional.isEmpty());
    }

    @Test
    void testFindByEmail_Success() {
        // Mockando o retorno do repositório para um usuário válido
        when(usuarioRepo.findByEmail("usuario@teste.com")).thenReturn(Optional.of(usuarioTecnico));

        Optional<Usuario> usuarioOptional = usuarioService.findByEmail("usuario@teste.com");

        assertTrue(usuarioOptional.isPresent());
        assertEquals("usuario@teste.com", usuarioOptional.get().getEmail());
    }

    @Test
    void testFindByEmail_Fail() {
        // Mockando o retorno do repositório para um email não encontrado
        when(usuarioRepo.findByEmail("email@naoexiste.com")).thenReturn(Optional.empty());

        Optional<Usuario> usuarioOptional = usuarioService.findByEmail("email@naoexiste.com");

        assertTrue(usuarioOptional.isEmpty());
    }
}
