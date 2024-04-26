package br.com.fiap.concessionaria.dto.response;


import jdk.jshell.Snippet;

public record AcessorioResponse(
        String nome,
        Double preco,
        Long id ) {

    public static Snippet builder() {
            return null;
    }
}
