package com.myzoul.curriculo.util;

import com.myzoul.curriculo.exception.BusinessException;
import com.myzoul.curriculo.model.CurriculoEnt;

public class PermissaoUtils {
    public static void checarPermissaoCpf(CurriculoEnt curriculo, String cpfUsuario, String mensagemErro) {
        if (!curriculo.getCpf().equals(cpfUsuario)) {
            throw new BusinessException(mensagemErro);
        }
    }
}