package com.myzoul.curriculo.repository;

import com.myzoul.curriculo.model.CurriculoEnt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CurriculoRepository extends JpaRepository<CurriculoEnt, Long> {
    Optional<CurriculoEnt> findByCpf(String cpf);

    @Query("SELECT c.escolaridade, COUNT(c) FROM CurriculoEnt c GROUP BY c.escolaridade")
    List<Object[]> contarPorEscolaridade();

    @Query("SELECT c.status, COUNT(c) FROM CurriculoEnt c GROUP BY c.status")
    List<Object[]> contarPorSituacao();
}