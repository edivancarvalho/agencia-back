package com.agencia.services.exceptions;

public class VagaNaoEncontradaException extends RuntimeException {

    public VagaNaoEncontradaException(Long id) {
        super("Vaga não encontrada para o ID: " + id);
    }
}
