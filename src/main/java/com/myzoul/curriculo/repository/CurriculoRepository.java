package com.myzoul.curriculo.repository;

import com.myzoul.curriculo.model.CurriculoEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurriculoRepository extends JpaRepository<CurriculoEnt, Long> {
}