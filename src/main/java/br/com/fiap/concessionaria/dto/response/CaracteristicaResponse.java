package br.com.fiap.concessionaria.dto.response;

import jdk.jshell.Snippet;

public record CaracteristicaResponse(
        String nome,
        String descricao,
        Long id,
        VeiculoResponse veiculo
) {
    public static Snippet builder() {
        return null;
    }
}
