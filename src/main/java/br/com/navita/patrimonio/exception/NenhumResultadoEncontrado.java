package br.com.navita.patrimonio.exception;

import java.util.function.Supplier;

public class NenhumResultadoEncontrado extends RuntimeException {

    public NenhumResultadoEncontrado() {
        super("Nenhum Resultado Encontrado");
    }

}
