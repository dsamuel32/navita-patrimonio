package br.com.navita.patrimonio.exception;

public class ValidacaoSenhaException extends RuntimeException {

    public ValidacaoSenhaException() {
        super("As senha informada não corresponde com a senha atual");
    }
}
