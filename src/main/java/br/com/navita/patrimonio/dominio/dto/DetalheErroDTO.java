package br.com.navita.patrimonio.dominio.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DetalheErroDTO {

    private LocalDateTime localDateTime;
    private List<String> erros;

    public DetalheErroDTO(LocalDateTime localDateTime, String mensagem) {
        this.localDateTime = localDateTime;
        this.erros = new ArrayList<>();
        this.erros.add(mensagem);
    }

    public DetalheErroDTO(LocalDateTime localDateTime, List<String> erros) {
        this.localDateTime = localDateTime;
        this.erros = erros;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
