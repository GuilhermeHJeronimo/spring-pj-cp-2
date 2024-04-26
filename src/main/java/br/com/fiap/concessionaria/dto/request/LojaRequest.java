package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;

public record LojaRequest(
        @NotNull(message = "INFORME O NOME DA LOJA")
        String nome
) {
}
