package com.myzoul.curriculo.controller;

import com.myzoul.curriculo.mapper.CurriculoMapper;
import com.myzoul.curriculo.model.CurriculoEnt;
import com.myzoul.curriculo.model.dto.CurriculoCreateDto;
import com.myzoul.curriculo.service.CurriculoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curriculos")
public class CurriculoController {
    private final CurriculoService service;

    public CurriculoController(CurriculoService service) {
        this.service = service;
    }

    @PostMapping
    public CurriculoEnt criar(@RequestBody CurriculoCreateDto dto) {
        CurriculoEnt entidade = CurriculoMapper.toEntity(dto);
        return service.salvar(entidade);
    }

    @GetMapping
    public List<CurriculoEnt> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public CurriculoEnt buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}