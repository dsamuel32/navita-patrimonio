package br.com.navita.patrimonio.exception;

public class RegistroNaoPodeSerApagadoException extends RuntimeException {

    public RegistroNaoPodeSerApagadoException(String mensagem) {
        super(mensagem);
    }
}
