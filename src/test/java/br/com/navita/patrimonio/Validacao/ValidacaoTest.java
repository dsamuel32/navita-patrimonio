package br.com.navita.patrimonio.Validacao;

import br.com.navita.patrimonio.exception.CamposInvalidosException;
import org.junit.Test;

import static org.junit.Assert.*;

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