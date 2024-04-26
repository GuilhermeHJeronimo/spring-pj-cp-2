package br.com.fiap.concessionaria.dto.response;

import jdk.jshell.Snippet;

public record FabricanteResponse(
        Long id,
        String nome,
        String nomeFantasia
) {
    public static Snippet builder() {
        return null;
    }
}
