package com.anhembi.a3.metro.a3_metro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.anhembi.a3.metro.a3_metro.enums.TipoAvisoEnum;
import com.anhembi.a3.metro.a3_metro.model.AvisoUsuario;
import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.repository.AvisoUsuarioRepo;

@SpringBootTest
public class AvisoUsuarioServiceTest {

    @InjectMocks
    private AvisoUsuarioService avisoUsuarioService;

    @Mock
    private AvisoUsuarioRepo avisoUsuarioRepo;

    private Usuario usuario;

    private AvisoUsuario avisoUsuario;

    @BeforeEach
    void setUp() {
        // Dados de entrada
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Bruno");
        usuario.setEmail("usuario@teste.com");
        usuario.setSenha("senha123");
        usuario.setTecnico(false);
        usuario.setDataCriacao(new Date());
        usuario.setDataModificacao(new Date());
        usuario.setAtivo(true);

        avisoUsuario = new AvisoUsuario();
        avisoUsuario.setId(1);
        avisoUsuario.setTipoAviso(TipoAvisoEnum.ATRASO);
        avisoUsuario.setUsuario(usuario);
    }

    @Test
    @Transactional
    public void create_retornaAvisoUsuarioSalvo_quandoSucesso() {
        // Mock
        when(avisoUsuarioRepo.save(avisoUsuario)).thenReturn(avisoUsuario);

        // Execução
        Optional<AvisoUsuario> resultado = avisoUsuarioService.create(avisoUsuario);

        // Verificações
        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getId());
        assertEquals(TipoAvisoEnum.ATRASO, resultado.get().getTipoAviso());
        assertEquals(usuario.getId(), resultado.get().getUsuario().getId());
    }

    @Test
    public void create_retornaOptionalEmpty_quandoFalhaAoSalvar() {
        AvisoUsuario avisoUsuario = new AvisoUsuario();
        avisoUsuario.setTipoAviso(TipoAvisoEnum.FALHA_TECNICA);

        // Mock com erro ao salvar
        when(avisoUsuarioRepo.save(avisoUsuario)).thenThrow(new RuntimeException("Erro ao salvar"));

        // Execução
        Optional<AvisoUsuario> resultado = avisoUsuarioService.create(avisoUsuario);

        // Verificações
        assertFalse(resultado.isPresent());
    }

    @Test
    public void findById_retornaAvisoUsuario_quandoIdValido() {
        int id = 1;
        AvisoUsuario avisoUsuario = new AvisoUsuario();
        avisoUsuario.setId(id);
        avisoUsuario.setTipoAviso(TipoAvisoEnum.ATRASO);

        // Mock
        when(avisoUsuarioRepo.findById(id)).thenReturn(Optional.of(avisoUsuario));

        // Execução
        Optional<AvisoUsuario> resultado = avisoUsuarioService.findById(id);

        // Verificações
        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        assertEquals(TipoAvisoEnum.ATRASO, resultado.get().getTipoAviso());
    }

    @Test
    public void findById_retornaOptionalEmpty_quandoIdInvalido() {
        int id = 0;

        // Mock para caso inválido
        when(avisoUsuarioRepo.findById(id)).thenReturn(Optional.empty());

        // Execução
        Optional<AvisoUsuario> resultado = avisoUsuarioService.findById(id);

        // Verificações
        assertFalse(resultado.isPresent());
    }

    @Test
    public void findAll_retornaListaDeAvisosUsuarios_quandoExistemAvisos() {
        List<AvisoUsuario> avisos = Arrays.asList(
                new AvisoUsuario() {
                    {
                        setId(1);
                        setTipoAviso(TipoAvisoEnum.ATRASO);
                    }
                },
                new AvisoUsuario() {
                    {
                        setId(2);
                        setTipoAviso(TipoAvisoEnum.FALHA_TECNICA);
                    }
                });

        // Mock
        when(avisoUsuarioRepo.findAll()).thenReturn(avisos);

        // Execução
        List<AvisoUsuario> resultado = avisoUsuarioService.findAll();

        // Verificações
        assertEquals(2, resultado.size());
        assertEquals(TipoAvisoEnum.ATRASO, resultado.get(0).getTipoAviso());
        assertEquals(TipoAvisoEnum.FALHA_TECNICA, resultado.get(1).getTipoAviso());
    }

    @Test
    public void findAll_retornaListaVazia_quandoNaoExistemAvisos() {
        // Mock
        when(avisoUsuarioRepo.findAll()).thenReturn(Collections.emptyList());

        // Execução
        List<AvisoUsuario> resultado = avisoUsuarioService.findAll();

        // Verificações
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void findAllByUsuario_retornaListaDeAvisos_quandoUsuarioValido() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        List<AvisoUsuario> avisos = Arrays.asList(
                new AvisoUsuario() {
                    {
                        setId(1);
                        setTipoAviso(TipoAvisoEnum.ATRASO);
                        setUsuario(usuario);
                    }
                });

        // Mock
        when(avisoUsuarioRepo.findAllByUsuario(usuario)).thenReturn(avisos);

        // Execução
        List<AvisoUsuario> resultado = avisoUsuarioService.findAllByUsuario(usuario);

        // Verificações
        assertEquals(1, resultado.size());
        assertEquals(usuario.getId(), resultado.get(0).getUsuario().getId());
    }

    @Test
    public void findAllByUsuario_retornaListaVazia_quandoUsuarioNulo() {
        // Execução
        List<AvisoUsuario> resultado = avisoUsuarioService.findAllByUsuario(null);

        // Verificações
        assertTrue(resultado.isEmpty());
    }

    @Test
    public void findAllByDataCriacao_retornaListaDeAvisos_quandoDataValida() {
        Date data = new Date();
        List<AvisoUsuario> avisos = Arrays.asList(
                new AvisoUsuario() {
                    {
                        setId(1);
                        setTipoAviso(TipoAvisoEnum.ATRASO);
                    }
                });

        // Mock
        when(avisoUsuarioRepo.findAllByDataCriacao(data)).thenReturn(avisos);

        // Execução
        List<AvisoUsuario> resultado = avisoUsuarioService.findAllByDataCriacao(data);

        // Verificações
        assertEquals(1, resultado.size());
        assertEquals(TipoAvisoEnum.ATRASO, resultado.get(0).getTipoAviso());
    }

    @Test
    public void findAllByDataCriacao_retornaListaVazia_quandoDataNula() {
        // Execução
        List<AvisoUsuario> resultado = avisoUsuarioService.findAllByDataCriacao(null);

        // Verificações
        assertTrue(resultado.isEmpty());
    }
}
