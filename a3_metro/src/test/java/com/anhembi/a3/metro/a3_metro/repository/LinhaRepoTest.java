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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.anhembi.a3.metro.a3_metro.model.Linha;

@SpringBootTest
@Transactional
@Rollback
public class LinhaRepoTest {

    @Autowired
    private LinhaRepo linhaRepo;

    private Linha linha;

 @BeforeEach
    void setUp() {
        linha = new Linha();
        linha.setNome("Linha Azul");
        linha.setDescricao("Linha que conecta a zona norte ao centro");
        linha.setAtivo(true);
        linha.setDataCriacao(new Date());
        linha.setDataModificacao(new Date());
        linhaRepo.save(linha);
    }

    @Test
    public void testSaveAndFindLinha() {
        Linha savedLinha = linhaRepo.save(linha);

        // Buscar a linha salva pelo ID
        Optional<Linha> foundLinha = linhaRepo.findById(savedLinha.getId());

        // Verificar se os dados salvos estão corretos
        assertTrue(foundLinha.isPresent());
        assertEquals("Linha Azul", foundLinha.get().getNome());
        assertEquals("Linha que conecta a zona norte ao centro", foundLinha.get().getDescricao());
    }

    @Test
    public void testUpdateLinha() {
        Linha savedLinha = linhaRepo.save(linha);

        // Atualizar a linha
        savedLinha.setDescricao("Linha atualizada: conecta o leste ao centro da cidade");
        Linha updatedLinha = linhaRepo.save(savedLinha);

        // Buscar a linha atualizada
        Optional<Linha> foundLinha = linhaRepo.findById(updatedLinha.getId());

        // Verificar se os dados foram atualizados
        assertTrue(foundLinha.isPresent());
        assertEquals("Linha atualizada: conecta o leste ao centro da cidade", foundLinha.get().getDescricao());
    }

    @Test
    public void testDeleteLinha() {
        Linha savedLinha = linhaRepo.save(linha);

        // Deletar a linha
        linhaRepo.delete(savedLinha);

        // Verificar se a linha foi deletada
        Optional<Linha> foundLinha = linhaRepo.findById(savedLinha.getId());
        assertFalse(foundLinha.isPresent());
    }

    @Test
    public void testFindAllLinhas() {
        Linha linha1 = new Linha();
        linha1.setNome("Linha Amarela");
        linha1.setDescricao("Linha que conecta o oeste ao centro");
        linha1.setAtivo(true);
        linha1.setDataCriacao(new Date());
        linha1.setDataModificacao(new Date());
        linhaRepo.save(linha1);

        Linha linha2 = new Linha();
        linha2.setNome("Linha Cinza");
        linha2.setDescricao("Linha nova em construção");
        linha2.setAtivo(true);
        linha2.setDataCriacao(new Date());
        linha2.setDataModificacao(new Date());
        linhaRepo.save(linha2);

        // Buscar todas as linhas
        Iterable<Linha> linhas = linhaRepo.findAll();

        // Verificar se as linhas foram retornadas
        assertNotNull(linhas);
        assertTrue(linhas.iterator().hasNext());
    }
}
