package com.anhembi.a3.metro.a3_metro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.anhembi.a3.metro.a3_metro.enums.TipoAvisoEnum;
import com.anhembi.a3.metro.a3_metro.model.Linha;
import com.anhembi.a3.metro.a3_metro.model.Noticia;
import com.anhembi.a3.metro.a3_metro.model.Usuario;
import com.anhembi.a3.metro.a3_metro.repository.NoticiaRepo;

@SpringBootTest
public class NoticiaServiceTest {

    @InjectMocks
    private NoticiaService noticiaService;

    @Mock
    private NoticiaRepo noticiaRepo;

    private Noticia noticia1;

    private Noticia noticia2;

    @BeforeEach
    void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Usuário Técnico");
        usuario.setEmail("tecnico@exemplo.com");
        usuario.setSenha("123456");
        usuario.setTecnico(true);

        Linha linha = new Linha();
        linha.setId(1);
        linha.setNome("Linha Vermelha");
        linha.setDescricao("Linha principal");

        noticia1 = new Noticia();
        noticia1.setId(1);
        noticia1.setDescricao("Atraso na linha");
        noticia1.setTipoAviso(TipoAvisoEnum.ATRASO);
        noticia1.setUsuario(usuario);
        noticia1.setLinha(linha);

        noticia2 = new Noticia();
        noticia2.setId(2);
        noticia2.setDescricao("Falha técnica");
        noticia2.setTipoAviso(TipoAvisoEnum.FALHA_TECNICA);
        noticia2.setUsuario(usuario);
        noticia2.setLinha(linha);
    }

    @Test
    public void findAll_retornaListaDeNoticias_quandoExistemNoticias() {
        List<Noticia> noticias = Arrays.asList(noticia1, noticia2);
        when(noticiaRepo.findAll()).thenReturn(noticias);

        List<Noticia> resultado = noticiaService.findAll();

        assertEquals(2, resultado.size());
        assertEquals("Atraso na linha", resultado.get(0).getDescricao());
        assertEquals(TipoAvisoEnum.ATRASO, resultado.get(0).getTipoAviso());
    }

    @Test
    public void create_retornaNoticiaSalva_quandoSucesso() {
        Noticia noticiaSalva = noticia1;
        noticiaSalva.setId(1);

        when(noticiaRepo.save(noticia1)).thenReturn(noticiaSalva);

        Optional<Noticia> resultado = noticiaService.create(noticia1);

        assertTrue(resultado.isPresent());
        assertEquals(1, resultado.get().getId());
        assertEquals("Atraso na linha", resultado.get().getDescricao());
        assertEquals(TipoAvisoEnum.ATRASO, resultado.get().getTipoAviso());
    }
}