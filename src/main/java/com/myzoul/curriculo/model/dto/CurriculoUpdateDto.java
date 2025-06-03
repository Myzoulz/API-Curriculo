package com.myzoul.curriculo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CurriculoUpdateDto(
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone,
        String escolaridade,
        String funcao,
        List<CompetenciaDto> competencias
) {}
