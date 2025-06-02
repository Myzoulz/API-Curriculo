package com.myzoul.curriculo.mapper;

import com.myzoul.curriculo.model.CurriculoEnt;
import com.myzoul.curriculo.model.CompetenciaEmb;
import com.myzoul.curriculo.model.dto.CurriculoCreateDto;

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
}