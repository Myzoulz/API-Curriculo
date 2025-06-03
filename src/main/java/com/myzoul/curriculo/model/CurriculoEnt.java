package com.myzoul.curriculo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ElementCollection
    @CollectionTable(name = "curriculo_competencia", joinColumns = @JoinColumn(name = "curriculo_id"))
    private List<CompetenciaEmb> competencias;

}