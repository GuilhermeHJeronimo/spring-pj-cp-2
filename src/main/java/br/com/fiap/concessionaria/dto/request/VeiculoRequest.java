package br.com.fiap.concessionaria.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.Year;

public record VeiculoRequest(
        @NotNull(message = "INFORME O PREÇO DO VEICULO")
        Double preco,
        @NotNull(message = "INFORME O MODELO")
        String modelo,
        @NotNull(message = "INFORME O ANO DE FABRICAÇÃO")
        Year anoDeFabricacao,
        @NotNull(message = "INFORME O NOME")
        String nome,
        @Valid
        @NotNull(message = "INFORME O TIPO")
        TipoVeiculoRequest tipo,
        @Valid
        @NotNull(message = "INFORME O FABRICANTE")
        FabricanteRequest fabricante,
        @NotNull(message = "INFORME A COR")
        String cor,
        @NotNull(message = "INFORME A PALAVRA DE EFEITO")
        @Size(min = 1, max = 15)
        String palavraDeEfeito,
        @NotNull(message = "INFORME AS CILINDRADAS")
        Short cilindradas
) {
}