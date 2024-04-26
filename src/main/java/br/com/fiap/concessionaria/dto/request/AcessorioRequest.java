package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.constraints.NotNull;

public record AcessorioRequest(
        @NotNull(message = "INFORME O NOME DO ACESSÓRIO")
        String nome,
        @NotNull(message = "INFORME O PREÇO DO ACESSÓRIO")
        Double preco
) {
        public String getNome() {
                return null;
        }

        public Double getPreco() {
                return null;
        }
}


