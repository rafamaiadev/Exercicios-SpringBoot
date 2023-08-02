package com.example.exerciciosspringb.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(@NotBlank String name, @NotNull BigDecimal price) {
}
