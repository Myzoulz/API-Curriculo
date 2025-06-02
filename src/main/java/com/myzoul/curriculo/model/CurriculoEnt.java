package com.myzoul.curriculo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class CurriculoEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;
    private String escolaridade;
    private String funcao;
    private String status;

    @ElementCollection
    @CollectionTable(name = "curriculo_competencia", joinColumns = @JoinColumn(name = "curriculo_id"))
    private List<CompetenciaEmb> competencias;

}