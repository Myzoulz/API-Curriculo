package com.myzoul.curriculo.service;

import com.myzoul.curriculo.exception.BusinessException;
import com.myzoul.curriculo.exception.NotFoundException;
import com.myzoul.curriculo.mapper.CurriculoMapper;
import com.myzoul.curriculo.model.CurriculoEnt;
import com.myzoul.curriculo.model.dto.CurriculoStatusDto;
import com.myzoul.curriculo.model.dto.CurriculoUpdateDto;
import com.myzoul.curriculo.repository.CurriculoRepository;
import com.myzoul.curriculo.util.PermissaoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CurriculoService {
    private static final Logger logger = LoggerFactory.getLogger(CurriculoService.class);

    private final CurriculoRepository repository;

    public CurriculoService(CurriculoRepository repository) {
        this.repository = repository;
    }

    public CurriculoEnt salvar(CurriculoEnt curriculo) {
        logger.info("Salvando currículo para CPF: {}", curriculo.getCpf());
        return repository.save(curriculo);
    }

    public CurriculoEnt atualizarParcial(Long id, CurriculoUpdateDto dto, String cpfUsuario) {
        CurriculoEnt existente = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Currículo não encontrado"));
        PermissaoUtils.checarPermissaoCpf(existente, cpfUsuario, "Você não tem permissão para alterar este currículo");
        CurriculoMapper.mergeUpdateDto(existente, dto);
        logger.info("Atualizando currículo id={}", id);
        return repository.save(existente);
    }

    public CurriculoStatusDto buscarStatusPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .map(c -> new CurriculoStatusDto(c.getStatus()))
                .orElse(new CurriculoStatusDto(null));
    }

    public Optional<CurriculoEnt> buscarCurriculoPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Map<String, Integer> contarPorEscolaridade() {
        List<Object[]> resultados = repository.contarPorEscolaridade();
        Map<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("Ignorado", 0);

        for (Object[] obj : resultados) {
            String escolaridade = (String) obj[0];
            if (escolaridade == null) escolaridade = "Ignorado";
            Integer qtd = ((Number) obj[1]).intValue();
            mapa.put(escolaridade, qtd);
        }
        return mapa;
    }

    public Map<String, Integer> contarPorSituacao() {
        List<Object[]> resultados = repository.contarPorSituacao();
        Map<String, Integer> mapa = new LinkedHashMap<>();
        mapa.put("aguardando", 0);
        mapa.put("aprovado", 0);
        mapa.put("reprovado", 0);

        for (Object[] obj : resultados) {
            String situacao = obj[0] != null ? obj[0].toString().toLowerCase() : "aguardando";
            Integer qtd = ((Number) obj[1]).intValue();
            mapa.put(situacao, qtd);
        }
        return mapa;
    }

    public CurriculoEnt atualizarStatus(Long id, String novoStatus) {
        String statusPadronizado = novoStatus.toLowerCase();
        if (!"aprovado".equals(statusPadronizado) && !"reprovado".equals(statusPadronizado)) {
            logger.warn("Status inválido informado: {}", novoStatus);
            throw new BusinessException("Status só pode ser 'aprovado' ou 'reprovado'");
        }
        CurriculoEnt curriculo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Currículo não encontrado"));
        curriculo.setStatus(statusPadronizado);
        logger.info("Status do currículo id={} atualizado para {}", id, statusPadronizado);
        return repository.save(curriculo);
    }

    public Page<CurriculoEnt> listarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public CurriculoEnt buscarPorIdUsuario(Long id, String cpfUsuario) {
        CurriculoEnt curriculo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Currículo não encontrado"));
        PermissaoUtils.checarPermissaoCpf(curriculo, cpfUsuario, "Acesso negado ao currículo de outro usuário");
        return curriculo;
    }
}