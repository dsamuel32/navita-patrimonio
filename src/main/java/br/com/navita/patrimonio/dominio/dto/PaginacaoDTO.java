package br.com.navita.patrimonio.dominio.dto;

import java.util.List;

public class PaginacaoDTO <T> {

    private Integer pagina;
    private Long total;
    private List<T> conteudo;

    public PaginacaoDTO(Integer pagina, Long total, List<T> conteudo) {
        this.pagina = pagina;
        this.total = total;
        this.conteudo = conteudo;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getConteudo() {
        return conteudo;
    }

    public void setConteudo(List<T> conteudo) {
        this.conteudo = conteudo;
    }
}
