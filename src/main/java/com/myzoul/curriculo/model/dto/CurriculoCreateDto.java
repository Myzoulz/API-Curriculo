package com.myzoul.curriculo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record CurriculoCreateDto(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull
        LocalDate dataNascimento,
        @NotBlank
        @Email
        String email,
        @Pattern(regexp = "^\\d{2}9\\d{8}$")
        @NotBlank
        String telefone,
        @NotBlank
        String escolaridade,
        @NotBlank
        String funcao,
        @NotNull
        @Size(min = 1)
        @Schema(
                description = "Lista de competências",
                implementation = CompetenciaDto.class
        )
        List<@Valid CompetenciaDto> competencias
) {}
