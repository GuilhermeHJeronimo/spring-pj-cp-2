package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record FabricanteRequest(
        @NotNull(message = "INFORME O NOME DO FABRICANTE")
        String nome,
        @NotNull(message = "INFORME O NOME FANTASIA DO FABRICANTE")
        String nomeFantasia
) {
        public Long id() {
                return null;
        }
}
