package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;


public record TipoVeiculoRequest(
        @NotNull(message = "INFORME O TIPO DO VEICULO")
        String nome
) {
        public Long id() {
                return null;
        }
}
