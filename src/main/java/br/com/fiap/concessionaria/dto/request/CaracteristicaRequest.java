package br.com.fiap.concessionaria.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CaracteristicaRequest(
        @Valid
        @NotNull(message = "INFORME O VEICULO")
        VeiculoRequest veiculo,
        @NotNull(message = "INFORME AS CARACTERISTICAS")
        @Size(min = 1, max = 30)
        String nome,
        @NotNull(message = "INFORME A DESCRIÇÃO DAS CARACTERISTICAS")
        @Size(min = 1, max = 20)
        String descricao
) {
}