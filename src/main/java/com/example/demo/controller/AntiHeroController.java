package com.example.demo.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.AntiHeroDto;
import com.example.demo.entity.AntiHeroEntity;
import com.example.demo.service.AntiHeroService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/anti-heroes")
@PreAuthorize("isAuthenticated()")
@Log4j2
public class AntiHeroController {

    private final AntiHeroService service;
    private final ModelMapper mapper;

    private AntiHeroDto convert2Dto(AntiHeroEntity entity) {
        return mapper.map(entity, AntiHeroDto.class);
    }

    private AntiHeroEntity convert2Entity(AntiHeroDto dto) {
        return mapper.map(dto, AntiHeroEntity.class);
    }

    @GetMapping("/{id}")
    public AntiHeroDto getAntiHeroById(@PathVariable("id") UUID id) {
        return convert2Dto(service.getAntiHeroById(id));
    }

    @PostMapping
    public AntiHeroDto saveAntiHero(@Valid @RequestBody AntiHeroDto dto) {
        var entity = convert2Entity(dto);
        var savedEntity = service.saveAntiHero(entity);
        return convert2Dto(savedEntity);
    }

    @PutMapping("/{id}")
    public void updateAntiHero(@PathVariable("id") UUID id, @Valid @RequestBody AntiHeroDto dto) {
        if (!id.equals(dto.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match.") ;
        var entity = convert2Entity(dto);
        service.updateAntiHero(id, entity);
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHero(@PathVariable("id") UUID id) {
        service.removeAntiHeroById(id);
    }

    @GetMapping
    public List<AntiHeroDto> getAllAntiHeroes() {
        log.info("Getting anti hero list");
        var antiHeroList = StreamSupport.stream(service.getAllAntiHeroes().spliterator(), false).collect(Collectors.toList());
        return antiHeroList.stream().map(this::convert2Dto).collect(Collectors.toList());
    }
}
