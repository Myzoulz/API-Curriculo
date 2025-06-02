package com.myzoul.curriculo.service;

import com.myzoul.curriculo.model.CurriculoEnt;
import com.myzoul.curriculo.repository.CurriculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculoService {
    private final CurriculoRepository repository;

    public CurriculoService(CurriculoRepository repository) {
        this.repository = repository;
    }

    public CurriculoEnt salvar(CurriculoEnt curriculo) {
        return repository.save(curriculo);
    }

    public List<CurriculoEnt> listarTodos() {
        return repository.findAll();
    }

    public Optional<CurriculoEnt> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}