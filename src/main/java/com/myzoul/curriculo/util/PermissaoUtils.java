package com.myzoul.curriculo.util;

import com.myzoul.curriculo.model.CurriculoEnt;
import org.springframework.security.access.AccessDeniedException;

public class PermissaoUtils {
    public static void checarPermissaoCpf(CurriculoEnt curriculo, String cpfUsuario, String mensagemErro) {
        if (!curriculo.getCpf().trim().equalsIgnoreCase(cpfUsuario.trim())) {
            throw new AccessDeniedException(mensagemErro);
        }
    }
}