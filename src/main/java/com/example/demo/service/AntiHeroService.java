package com.example.demo.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.entity.AntiHeroEntity;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.AntiHeroRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AntiHeroService {

    private AntiHeroRepository repo;

    public Iterable<AntiHeroEntity> getAllAntiHeroes() {
        return repo.findAll();
    }

    public AntiHeroEntity getAntiHeroById(UUID id) {
        return findOrThrow(id);
    }

    public void removeAntiHeroById(UUID id) {
        findOrThrow(id);
        repo.deleteById(id);
    }

    public AntiHeroEntity saveAntiHero(AntiHeroEntity antiHero) {
        return repo.save(antiHero);
    }

    public void updateAntiHero(UUID id, AntiHeroEntity antiHero) {
        findOrThrow(id);
        repo.save(antiHero);
    }

    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo.findById(id).orElseThrow (() -> new NotFoundException("Anti-hero by id " + id + " was not found"));
    }
}
