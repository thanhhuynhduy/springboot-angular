package com.example.demo.service;

import com.example.demo.entity.AntiHeroEntity;
import com.example.demo.repository.AntiHeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AntiHeroServiceTest {

    @Mock
    private AntiHeroRepository repository;

    @InjectMocks
    private AntiHeroService service;

    @Test
    void canFindAllHeroes() {
        service.getAllAntiHeroes();
        verify(repository).findAll();
    }

    @Test
    void canAddAntiHero() {
        AntiHeroEntity antiHero = new AntiHeroEntity(
                UUID.randomUUID(),
                "Bunao",
                "Lakandula",
                "Tondo",
                "Datu of Tondo",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z").format(new Date())
        );

        service.saveAntiHero(antiHero);
        ArgumentCaptor<AntiHeroEntity> antiHeroEntityArgumentCaptor = ArgumentCaptor.forClass(AntiHeroEntity.class);
        verify(repository).save(antiHeroEntityArgumentCaptor.capture());
        AntiHeroEntity capturedAntiHero = antiHeroEntityArgumentCaptor.getValue();
        assertThat(capturedAntiHero).isEqualTo(antiHero);
    }
}
