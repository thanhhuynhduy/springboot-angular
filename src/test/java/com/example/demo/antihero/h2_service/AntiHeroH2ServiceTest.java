package com.example.demo.antihero.h2_service;

import com.example.demo.entity.AntiHeroEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.AntiHeroRepository;
import com.example.demo.service.AntiHeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class AntiHeroH2ServiceTest {

    private AntiHeroService antiHeroService;
    @Autowired
    private AntiHeroRepository antiHeroRepository;

    AntiHeroEntity antiHero = new AntiHeroEntity();

    @BeforeEach
    public void setup() {
        antiHero.setFirstName("Thanh");
        antiHero.setLastName("Huynh");
        antiHero.setHouse("My Tho");
        antiHeroService = new AntiHeroService(antiHeroRepository);
    }

    @Test
    public void shouldFindAllAntiHero() {
        antiHeroService.saveAntiHero(antiHero);
        Iterable<AntiHeroEntity> antiHeroList = antiHeroService.getAllAntiHeroes();
        AntiHeroEntity savedAntiHero = antiHeroList.iterator().next();
        assertThat(savedAntiHero).isNotNull();
    }

    @Test
    public void shouldAddAntiHero() {
        antiHeroService.saveAntiHero(antiHero);
        Iterable<AntiHeroEntity> antiHeroList = antiHeroService.getAllAntiHeroes();
        AntiHeroEntity savedAntiHero = antiHeroList.iterator().next();
        assertThat(savedAntiHero).isEqualTo(antiHero);
    }

    @Test
    public void shouldUpdateAntiHero() {
        AntiHeroEntity savedAntiHero = antiHeroService.saveAntiHero(antiHero);
        savedAntiHero.setHouse("Soc Trang");
        antiHeroService.updateAntiHero(savedAntiHero.getId(), savedAntiHero);
        AntiHeroEntity foundAntihero = antiHeroService.getAntiHeroById(savedAntiHero.getId());
        assertThat(foundAntihero.getHouse()).isEqualTo("Soc Trang");
    }

    @Test
    public void shouldDeleteAntiHero() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                AntiHeroEntity savedAntiHero = antiHeroService.saveAntiHero(antiHero);
                antiHeroService.removeAntiHeroById(savedAntiHero.getId());
                AntiHeroEntity foundAntiHero = antiHeroService.getAntiHeroById(savedAntiHero.getId());
                assertThat(foundAntiHero).isNull();
            }
        });
    }
}
