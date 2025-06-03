package com.myzoul.curriculo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Competência do currículo")
public record CompetenciaDto(
        @NotBlank
        @Schema(description = "Descrição da competência", example = "Java")
        String descricao,
        @NotBlank
        @Schema(description = "Nível da competência", example = "Avançado")
        String nivel
) {}
