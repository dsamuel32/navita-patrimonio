package br.com.navita.patrimonio.dominio.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class FiltroPaginacaoDTO {

    protected Integer pagina = 0;
    protected Integer limite = 10;
    protected Sort.Direction ordenacao = Sort.Direction.ASC;

    public Pageable pageable() {
        return PageRequest.of(pagina,  limite, ordenacao, "id");
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public Sort.Direction getOrdenacao() {
        return ordenacao;
    }

    public void setOrdenacao(Sort.Direction ordenacao) {
        this.ordenacao = ordenacao;
    }
}
