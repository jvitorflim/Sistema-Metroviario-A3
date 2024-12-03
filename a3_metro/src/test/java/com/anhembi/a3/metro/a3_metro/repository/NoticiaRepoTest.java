package com.anhembi.a3.metro.a3_metro.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.anhembi.a3.metro.a3_metro.enums.TipoAvisoEnum;
import com.anhembi.a3.metro.a3_metro.model.Linha;
import com.anhembi.a3.metro.a3_metro.model.Noticia;
import com.anhembi.a3.metro.a3_metro.model.Usuario;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class NoticiaRepoTest {

    @Autowired
    private NoticiaRepo noticiaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo; 

    @Autowired
    private LinhaRepo linhaRepo;

    private Usuario usuario;

    private Linha linha;

    private Noticia noticia1;

    private Noticia noticia2;

    @BeforeEach
    void setUp() {
        // Criar um usuário
        usuario = new Usuario();
        usuario = new Usuario();
        usuario.setNome("Usuário Teste");
        usuario.setEmail("teste@exemplo.com");
        usuario.setSenha("123456");
        usuario.setTecnico(false);
        usuario.setAtivo(true);
        usuario.setDataCriacao(new Date());
        usuario.setDataModificacao(new Date());
        usuarioRepo.save(usuario);

        // Criar uma linha
        linha = new Linha();
        linha.setNome("Linha Azul");
        linha.setDescricao("Linha que conecta a zona norte ao centro");
        linha.setAtivo(true);
        linha.setDataCriacao(new Date());
        linha.setDataModificacao(new Date());
        linhaRepo.save(linha);

        // Criar notícias associadas ao usuário
        noticia1 = new Noticia();
        noticia1.setDescricao("Interrupção na Linha Azul");
        noticia1.setTipoAviso(TipoAvisoEnum.FALHA_TECNICA);
        noticia1.setUsuario(usuario);
        noticia1.setLinha(linha);
        noticia1.setAtivo(true);
        noticia1.setDataCriacao(new Date());
        noticia1.setDataModificacao(new Date());
        noticiaRepo.save(noticia1);

        noticia2 = new Noticia();
        noticia2.setDescricao("Alerta de chuva na Linha Vermelha");
        noticia2.setTipoAviso(TipoAvisoEnum.ATRASO);
        noticia2.setUsuario(usuario);
        noticia2.setLinha(linha);
        noticia2.setAtivo(true);
        noticia2.setDataCriacao(new Date());
        noticia2.setDataModificacao(new Date());
        noticiaRepo.save(noticia2);
    }

    @Test
    public void testFindAllByUsuario() {
        // Buscar todas as notícias do usuário
        List<Noticia> noticias = noticiaRepo.findAllByUsuario(usuario);

        // Verificar se as notícias retornadas estão corretas
        assertNotNull(noticias);
        assertEquals(2, noticias.size());
        assertTrue(noticias.stream().anyMatch(n -> n.getDescricao().equals("Interrupção na Linha Azul")));
        assertTrue(noticias.stream().anyMatch(n -> n.getDescricao().equals("Alerta de chuva na Linha Vermelha")));
    }

    @Test
    public void testFindAllByDataCriacao() {
        // Buscar todas as notícias criadas na data atual
        List<Noticia> noticias = noticiaRepo.findAllByDataCriacao(new Date());

        // Verificar se as notícias retornadas estão corretas
        assertNotNull(noticias);
        assertEquals(2, noticias.size());
        assertTrue(noticias.stream().anyMatch(n -> n.getDescricao().equals("Alerta de chuva na Linha Vermelha")));
        assertTrue(
                noticias.stream().anyMatch(n -> n.getDescricao().equals("Interrupção na Linha Azul")));
    }
}