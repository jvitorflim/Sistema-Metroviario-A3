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
import org.springframework.transaction.annotation.Transactional;

import com.anhembi.a3.metro.a3_metro.model.Estacao;
import com.anhembi.a3.metro.a3_metro.model.Linha;

@SpringBootTest
public class EstacaoRepoTest {

    @Autowired
    private EstacaoRepo estacaoRepo;

    @Autowired
    private LinhaRepo linhaRepo;

    private Estacao estacao;

    private Linha linha;

    @BeforeEach
    void setUp() {
        linha = new Linha();
        //linha.setId(1);
        linha.setNome("Linha Azul");
        linha.setDescricao("Linha que conecta a zona norte ao centro");
        linha.setAtivo(true);
        linha.setDataCriacao(new Date());
        linha.setDataModificacao(new Date());

        estacao = new Estacao();
        estacao.setId(1);
        estacao.setNome("Estação Central");
        estacao.setCodigo("CEN01");
        estacao.setOrdem(1);
        estacao.setLocalizacao("Centro da Cidade");
        estacao.setLinha(linha);
        estacao.setAtivo(true);
        estacao.setDataCriacao(new Date());
        estacao.setDataModificacao(new Date());
    }

    @Test
    public void testSaveAndFindEstacao() {
        // Salvar no banco de dados
        linhaRepo.save(linha);
        Estacao savedEstacao = estacaoRepo.save(estacao);

        // Buscar a estação salva pelo ID
        Optional<Estacao> foundEstacao = estacaoRepo.findById(savedEstacao.getId());

        // Verificar se os dados estão corretos
        assertTrue(foundEstacao.isPresent());
        assertEquals("Estação Central", foundEstacao.get().getNome());
        assertEquals("CEN01", foundEstacao.get().getCodigo());
        assertEquals(1, foundEstacao.get().getOrdem());
        assertEquals("Centro da Cidade", foundEstacao.get().getLocalizacao());
        assertNotNull(foundEstacao.get().getLinha());
        assertEquals("Linha Azul", foundEstacao.get().getLinha().getNome());
    }

    @Test
    @Transactional
    public void testUpdateEstacao() {
        // Criar uma estação associada à linha
        Estacao estacaoUpdate = estacao;
        estacaoUpdate.setNome("Estação Leste");
        estacaoUpdate.setCodigo("LES01");
        estacaoUpdate.setOrdem(3);
        estacaoUpdate.setLocalizacao("Zona Leste Atualizada");
        estacaoRepo.save(estacaoUpdate);

        // Buscar a estação atualizada
        Optional<Estacao> foundEstacao = estacaoRepo.findById(estacaoUpdate.getId());

        // Verificar se as atualizações foram persistidas
        assertTrue(foundEstacao.isPresent());
        assertEquals("Zona Leste Atualizada", foundEstacao.get().getLocalizacao());
        assertEquals(3, foundEstacao.get().getOrdem());
    }

    @Test
    public void testDeleteEstacao() {
        // Deletar a estação
        estacaoRepo.delete(estacao);

        // Verificar se a estação foi deletada
        Optional<Estacao> foundEstacao = estacaoRepo.findById(estacao.getId());
        assertFalse(foundEstacao.isPresent());
    }

    @Test
    public void testFindAllEstacoes() {
        linhaRepo.save(linha);

        // Criar estações associadas à linha
        Estacao estacao1 = new Estacao();
        estacao1.setNome("Estação Sul 1");
        estacao1.setCodigo("SUL01");
        estacao1.setOrdem(1);
        estacao1.setLocalizacao("Zona Sul");
        estacao1.setLinha(linha);
        estacao1.setAtivo(true);
        estacao1.setDataCriacao(new Date());
        estacao1.setDataModificacao(new Date());
        estacaoRepo.save(estacao1);

        Estacao estacao2 = new Estacao();
        estacao2.setNome("Estação Sul 2");
        estacao2.setCodigo("SUL02");
        estacao2.setOrdem(2);
        estacao2.setLocalizacao("Zona Sul");
        estacao2.setLinha(linha);
        estacao2.setAtivo(true);
        estacao2.setDataCriacao(new Date());
        estacao2.setDataModificacao(new Date());
        estacaoRepo.save(estacao2);

        // Buscar todas as estações
        Iterable<Estacao> estacoes = estacaoRepo.findAll();

        // Verificar se as estações foram retornadas
        assertNotNull(estacoes);
        assertTrue(estacoes.iterator().hasNext());
    }
}