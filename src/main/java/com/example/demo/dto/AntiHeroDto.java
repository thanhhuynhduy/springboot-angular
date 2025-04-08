package com.example.demo.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AntiHeroDto {

    private UUID id;

    @NotNull(message = " First name is required")
    private String firstName;

    private String lastName;
    private String house;
    private String knownAs;
}
