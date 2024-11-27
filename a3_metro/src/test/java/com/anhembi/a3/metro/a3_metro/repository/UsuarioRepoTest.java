package com.anhembi.a3.metro.a3_metro.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.anhembi.a3.metro.a3_metro.model.Usuario;

import jakarta.transaction.Transactional;

@SpringBootTest
public class UsuarioRepoTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        // Configuração inicial de um usuário para os testes
        usuario = new Usuario();
        usuario.setNome("Usuário Teste");
        usuario.setEmail("teste@exemplo.com");
        usuario.setSenha("123456");
        usuario.setTecnico(false);
        usuario.setAtivo(true);
        usuario.setDataCriacao(new Date());
        usuario.setDataModificacao(new Date());
    }

    @Test
    @Transactional
    public void findByEmail_retornaUsuario_quandoEmailExiste() {
        // Recuperando o usuário pelo email
        Optional<Usuario> usuarioEncontrado = usuarioRepo.findByEmail("teste@exemplo.com");

        // Verificando os resultados
        assertTrue(usuarioEncontrado.isPresent());
        assertEquals("Usuário Teste", usuarioEncontrado.get().getNome());
        assertEquals("teste@exemplo.com", usuarioEncontrado.get().getEmail());
        assertFalse(usuarioEncontrado.get().isTecnico());
    }

    @Test
    public void findByEmail_retornaOptionalEmpty_quandoEmailNaoExiste() {
        // Recuperando um usuário inexistente
        Optional<Usuario> usuarioEncontrado = usuarioRepo.findByEmail("inexistente@exemplo.com");

        // Verificando que o resultado é vazio
        assertFalse(usuarioEncontrado.isPresent());
    }

    @Test
    public void save_persisteUsuario_quandoDadosValidos() {
        // Salvando um usuário no banco
        Usuario usuarioSalvo = usuarioRepo.save(usuario);

        // Verificando que o ID foi gerado
        assertNotNull(usuarioSalvo.getId());
        assertEquals("Usuário Teste", usuarioSalvo.getNome());
        assertEquals("teste@exemplo.com", usuarioSalvo.getEmail());
        assertEquals("123456", usuarioSalvo.getSenha());
        assertFalse(usuarioSalvo.isTecnico());
    }

    @Test
    public void delete_removeUsuario_quandoIdValido() {
        // Salvando um usuário no banco
        Usuario usuarioSalvo = usuario;

        // Removendo o usuário
        usuarioRepo.deleteById(usuarioSalvo.getId());

        // Verificando se o usuário foi removido
        Optional<Usuario> usuarioRemovido = usuarioRepo.findById(usuarioSalvo.getId());
        assertFalse(usuarioRemovido.isPresent());
    }
}
