package com.myzoul.curriculo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CurriculoUpdateDto(
        String nome,
        @Pattern(regexp = "\\d{11}")
        String cpf,
        LocalDate dataNascimento,
        @Email
        String email,
        @Pattern(regexp = "^\\d{2}9\\d{8}$")
        String telefone,
        String escolaridade,
        String funcao,
        List<CompetenciaDto> competencias
) {}
