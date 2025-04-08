package com.example.demo.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.AntiHeroEntity;

public interface AntiHeroRepository extends CrudRepository<AntiHeroEntity, UUID> {

}
