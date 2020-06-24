package br.com.navita.patrimonio.exception;

public class NenhumResultadoEncontrado extends RuntimeException {

    public NenhumResultadoEncontrado() {
        super("Nenhum Resultado Encontrado");
    }

    public NenhumResultadoEncontrado(String mensagem) {
        super(mensagem);
    }

}
