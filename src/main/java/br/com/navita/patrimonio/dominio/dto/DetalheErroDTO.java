package br.com.navita.patrimonio.dominio.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DetalheErroDTO {

    private LocalDateTime localDateTime;
    private String mensagem;
    private List<String> erros;

    public DetalheErroDTO(LocalDateTime localDateTime, String mensagem) {
        this.localDateTime = localDateTime;
        this.mensagem = mensagem;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
