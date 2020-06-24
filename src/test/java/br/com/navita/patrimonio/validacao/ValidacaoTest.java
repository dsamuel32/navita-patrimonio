package br.com.navita.patrimonio.validacao;

import br.com.navita.patrimonio.exception.CamposInvalidosException;
import org.junit.Test;

public class ValidacaoTest {

    @Test(expected = CamposInvalidosException.class)
    public void validar() {
        Long id = null;
        Integer numero = null;
        String nome = null;
        Validacao validacao = new Validacao();
        validacao.campoPreenchido("id", id)
                 .campoPreenchido("numero", numero)
                 .campoPreenchido("nome", nome)
                 .validar();

    }
}