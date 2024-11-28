package com.anhembi.a3.metro.a3_metro.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.anhembi.a3.metro.a3_metro.enums.StatusLotacaoEnum;
import com.anhembi.a3.metro.a3_metro.model.Trem;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional // Garante que as alterações no banco de dados sejam revertidas após cada teste
@Rollback // Reverte as alterações automaticamente, mesmo com @Transactional
public class TremRepoTest {

    @Autowired
    private TremRepo tremRepo;

    private Trem trem;

    @BeforeEach
    void setUp() {
        trem = new Trem();
        trem.setIdentificacao("Trem-01");
        trem.setVelocidade(80.0);
        trem.setVelocidadeMaxima(120.0);
        trem.setArCondicionado(true);
        trem.setStatusLotacao(StatusLotacaoEnum.POUCO_LOTADO);
        trem.setAtivo(true);
        trem.setDataCriacao(new Date());
        trem.setDataModificacao(new Date());
    }

    @Test
    public void testSaveAndFindTrem() {
        Trem savedTrem = tremRepo.save(trem);

        Trem foundTrem = tremRepo.findById(savedTrem.getId()).orElse(null);

        // Verificações
        assertThat(foundTrem).isNotNull();
        assertThat(foundTrem.getIdentificacao()).isEqualTo("Trem-01");
        assertThat(foundTrem.getVelocidade()).isEqualTo(80.0);
        assertThat(foundTrem.isArCondicionado()).isTrue();
    }
}