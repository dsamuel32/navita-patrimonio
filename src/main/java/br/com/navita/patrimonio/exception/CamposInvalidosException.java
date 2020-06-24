package br.com.navita.patrimonio.exception;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class CamposInvalidosException extends RuntimeException {

    private List<String> erros;

    public CamposInvalidosException(String mensagem) {
        super(mensagem);
        this.erros = new ArrayList<>();
        this.erros.add(mensagem);
    }

    public CamposInvalidosException(ConstraintViolationException e) {
        this.erros = new ArrayList<>();
        e.getConstraintViolations().forEach(it -> erros.add(it.getMessage()));
    }

    public CamposInvalidosException(Throwable cause) {
        this.erros = new ArrayList<>();
        if (cause instanceof ConstraintViolationException) {
            ((ConstraintViolationException) cause).getConstraintViolations().forEach(it -> erros.add(it.getMessage()));
        } else {
            ConstraintViolationException ex = (ConstraintViolationException) cause.getCause().getCause();
            ex.getConstraintViolations().forEach(it -> erros.add(it.getMessage()));
        }
    }

    public List<String> getErros() {
        return this.erros;
    }

}
