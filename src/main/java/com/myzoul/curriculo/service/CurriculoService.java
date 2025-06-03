package com.myzoul.curriculo.service;

import com.myzoul.curriculo.mapper.CurriculoMapper;
import com.myzoul.curriculo.model.CurriculoEnt;
import com.myzoul.curriculo.model.dto.CurriculoUpdateDto;
import com.myzoul.curriculo.repository.CurriculoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<CurriculoEnt> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<CurriculoEnt> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public CurriculoEnt atualizarParcial(Long id, CurriculoUpdateDto dto) {
        CurriculoEnt existente = repository.findById(id).orElseThrow();
        CurriculoMapper.mergeUpdateDto(existente, dto);
        return repository.save(existente);
    }
}