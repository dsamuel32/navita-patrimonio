package br.com.navita.patrimonio.validacao;

import br.com.navita.patrimonio.exception.CamposInvalidosException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validacao {

    private List<String> erros;
    private Map<String, Object> campos;

    public Validacao() {
        this.erros = new ArrayList<>();
        this.campos = new HashMap<>();
    }

    public static Validacao newInstance() {
        return new Validacao();
    }

    public Validacao campoPreenchido(String campo, String valor) {
        if (this.isNull(valor) || this.isTextoVazio(valor)) {
            StringBuilder sb = new StringBuilder("O campo ");
            sb.append(campo).append(" não pode ser vazio ou nulo");
            this.erros.add(sb.toString());
        }
        return this;
    }

    public Validacao campoPreenchido(String campo, Integer valor) {
        this.verificarCampoNulo(campo, valor);
        return this;
    }

    public Validacao campoPreenchido(String campo, Long valor) {
        this.verificarCampoNulo(campo, valor);
        return this;
    }

    private void verificarCampoNulo(String campo, Object valor) {
        if (this.isNull(valor)) {
            StringBuilder sb = new StringBuilder("O campo ");
            sb.append(campo).append(" não pode ser nulo");
            this.erros.add(sb.toString());
        }
    }

    private Boolean isNull(Object valor) {
        return valor == null;
    }

    private Boolean isTextoVazio(Object valor) {
        return valor == "";
    }

    public void validar() {
        if (!this.erros.isEmpty()) {
            throw new CamposInvalidosException(this.erros);
        }
    }

}
