package com.myzoul.curriculo.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CompetenciaDto(
        @NotBlank
        String descricao,
        @NotBlank
        String nivel
) {}
