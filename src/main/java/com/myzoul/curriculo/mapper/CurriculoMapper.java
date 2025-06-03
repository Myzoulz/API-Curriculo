package com.myzoul.curriculo.mapper;

import com.myzoul.curriculo.model.CurriculoEnt;
import com.myzoul.curriculo.model.CompetenciaEmb;
import com.myzoul.curriculo.model.dto.CurriculoCreateDto;
import com.myzoul.curriculo.model.dto.CurriculoUpdateDto;

import java.util.stream.Collectors;

public class CurriculoMapper {
    public static CurriculoEnt toEntity(CurriculoCreateDto dto) {
        CurriculoEnt entidade = new CurriculoEnt();
        entidade.setNome(dto.nome());
        entidade.setCpf(dto.cpf());
        entidade.setDataNascimento(dto.dataNascimento());
        entidade.setEmail(dto.email());
        entidade.setTelefone(dto.telefone());
        entidade.setEscolaridade(dto.escolaridade());
        entidade.setFuncao(dto.funcao());
        entidade.setCompetencias(
                dto.competencias().stream().map(c -> {
                    CompetenciaEmb emb = new CompetenciaEmb();
                    emb.setDescricao(c.descricao());
                    emb.setNivel(c.nivel());
                    return emb;
                }).collect(Collectors.toList())
        );
        return entidade;
    }

    public static void mergeUpdateDto(CurriculoEnt entidade, CurriculoUpdateDto dto) {
        if (dto.nome() != null) entidade.setNome(dto.nome());
        if (dto.cpf() != null) entidade.setCpf(dto.cpf());
        if (dto.dataNascimento() != null) entidade.setDataNascimento(dto.dataNascimento());
        if (dto.email() != null) entidade.setEmail(dto.email());
        if (dto.telefone() != null) entidade.setTelefone(dto.telefone());
        if (dto.escolaridade() != null) entidade.setEscolaridade(dto.escolaridade());
        if (dto.funcao() != null) entidade.setFuncao(dto.funcao());
        if (dto.competencias() != null) {
            entidade.setCompetencias(
                    dto.competencias().stream()
                            .map(c -> new CompetenciaEmb(c.descricao(), c.nivel()))
                            .collect(Collectors.toList())
            );
        }
    }
}