package com.anhembi.a3.metro.a3_metro.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.anhembi.a3.metro.a3_metro.enums.TipoAvisoEnum;
import com.anhembi.a3.metro.a3_metro.model.AvisoUsuario;
import com.anhembi.a3.metro.a3_metro.model.Usuario;

@SpringBootTest
@Transactional
public class AvisoUsuarioRepoTest {

    @Autowired
    private AvisoUsuarioRepo avisoUsuarioRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    private Usuario usuario;
    private AvisoUsuario avisoUsuario;

    @BeforeEach
    void setUp() {
        // Configurar o objeto usuário padrão
        usuario = new Usuario();
        usuario.setNome("Usuário Teste");
        usuario.setEmail("teste@exemplo.com");
        usuario.setSenha("123456");
        usuario.setTecnico(false);
        usuario.setAtivo(true);
        usuario.setDataCriacao(new Date());
        usuario.setDataModificacao(new Date());

        // Salvar o usuário no banco
        usuario = usuarioRepo.save(usuario);

        // Configurar o objeto aviso padrão
        avisoUsuario = new AvisoUsuario();
        avisoUsuario.setTipoAviso(TipoAvisoEnum.ATRASO);
        avisoUsuario.setUsuario(usuario);
        avisoUsuario.setDataCriacao(new Date());
        avisoUsuario.setDataModificacao(new Date());
        avisoUsuario.setAtivo(true);

        // Salvar o aviso no banco
        avisoUsuario = avisoUsuarioRepo.save(avisoUsuario);
    }

    @Test
    public void testSaveAndFindAvisoUsuario() {
        // Buscar o aviso pelo ID
        Optional<AvisoUsuario> foundAviso = avisoUsuarioRepo.findById(avisoUsuario.getId());

        // Verificar se os dados estão corretos
        assertTrue(foundAviso.isPresent());
        assertEquals(TipoAvisoEnum.ATRASO, foundAviso.get().getTipoAviso());
        assertNotNull(foundAviso.get().getUsuario());
        assertEquals("Usuário Teste", foundAviso.get().getUsuario().getNome());
    }

    @Test
    public void testFindAllByUsuario() {
        // Criar outro aviso associado ao mesmo usuário
        AvisoUsuario outroAviso = new AvisoUsuario();
        outroAviso.setTipoAviso(TipoAvisoEnum.SUPERLOTACAO);
        outroAviso.setUsuario(usuario);
        outroAviso.setDataCriacao(new Date());
        outroAviso.setDataModificacao(new Date());
        outroAviso.setAtivo(true);
        avisoUsuarioRepo.save(outroAviso);

        // Buscar todos os avisos do usuário
        List<AvisoUsuario> avisos = avisoUsuarioRepo.findAllByUsuario(usuario);

        // Verificar se os avisos estão corretos
        assertNotNull(avisos);
        assertEquals(2, avisos.size());
        assertTrue(avisos.stream().anyMatch(a -> a.getTipoAviso() == TipoAvisoEnum.ATRASO));
        assertTrue(avisos.stream().anyMatch(a -> a.getTipoAviso() == TipoAvisoEnum.SUPERLOTACAO));
    }

    @Test
    public void testFindAllByDataCriacao() {
        // Criar outro aviso com a mesma data de criação
        Date dataCriacao = avisoUsuario.getDataCriacao();

        AvisoUsuario outroAviso = new AvisoUsuario();
        outroAviso.setTipoAviso(TipoAvisoEnum.SUPERLOTACAO);
        outroAviso.setUsuario(usuario);
        outroAviso.setDataCriacao(dataCriacao);
        outroAviso.setDataModificacao(new Date());
        outroAviso.setAtivo(true);
        avisoUsuarioRepo.save(outroAviso);

        // Buscar todos os avisos pela data de criação
        List<AvisoUsuario> avisos = avisoUsuarioRepo.findAllByDataCriacao(dataCriacao);

        // Verificar se os avisos estão corretos
        assertNotNull(avisos);
        assertEquals(2, avisos.size());
    }

    @Test
    public void testDeleteAvisoUsuario() {
        // Deletar o aviso padrão
        avisoUsuarioRepo.delete(avisoUsuario);

        // Verificar se o aviso foi deletado
        Optional<AvisoUsuario> foundAviso = avisoUsuarioRepo.findById(avisoUsuario.getId());
        assertFalse(foundAviso.isPresent());
    }
}