package com.myzoul.curriculo.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CompetenciaEmb {
    private String descricao;
    private String nivel;
}