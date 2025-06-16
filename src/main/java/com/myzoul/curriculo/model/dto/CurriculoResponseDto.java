package com.myzoul.curriculo.model.dto;

import java.time.LocalDate;
import java.util.List;

public record CurriculoResponseDto(
        Long id,
        String nome,
        String cpf,
        LocalDate dataNascimento,
        String email,
        String telefone,
        String escolaridade,
        String funcao,
        List<CompetenciaDto> competencias,
        String status
) {}