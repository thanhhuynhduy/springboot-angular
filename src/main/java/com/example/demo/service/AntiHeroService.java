package com.example.demo.service;

import java.util.UUID;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AntiHeroEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.AntiHeroRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AntiHeroService {

    private AntiHeroRepository repo;

    @Cacheable(value = "heroes")
    public Iterable<AntiHeroEntity> getAllAntiHeroes() {
        return repo.findAll();
    }

    @Cacheable(value = "heroes", key = "#id")
    public AntiHeroEntity getAntiHeroById(UUID id) {
        return findOrThrow(id);
    }

    @CacheEvict(value = "heroes", key = "#id")
    public void removeAntiHeroById(UUID id) {
        findOrThrow(id);
        repo.deleteById(id);
    }

    @CacheEvict(value = "heroes", key = "#antiHero.id")
    public AntiHeroEntity saveAntiHero(AntiHeroEntity antiHero) {
        return repo.save(antiHero);
    }

    @CacheEvict(value = "heroes", key = "#id")
    public void updateAntiHero(UUID id, AntiHeroEntity antiHero) {
        findOrThrow(id);
        repo.save(antiHero);
    }

    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo.findById(id).orElseThrow (() -> new NotFoundException("Anti-hero by id " + id + " was not found"));
    }
}
