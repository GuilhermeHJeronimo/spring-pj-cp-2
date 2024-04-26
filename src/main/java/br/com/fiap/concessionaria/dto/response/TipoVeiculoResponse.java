package br.com.fiap.concessionaria.dto.response;

import jdk.jshell.Snippet;

public record TipoVeiculoResponse(
        Long id,
        String nome
) {
    public static Snippet builder() {
        return null;
    }
}
