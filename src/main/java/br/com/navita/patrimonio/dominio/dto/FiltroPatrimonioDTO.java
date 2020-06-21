package br.com.navita.patrimonio.dominio.dto;

public class FiltroPatrimonioDTO extends FiltroPaginacaoDTO {

    private String numeroTombo;
    private String nome;
    private String descricao;

    public String getNumeroTombo() {
        if (isNotNull(this.numeroTombo)) {
            return this.numeroTombo;
        }
        return numeroTombo;
    }

    public void setNumeroTombo(String numeroTombo) {
        this.numeroTombo = numeroTombo;
    }

    public String getNome() {
        if (this.isNotNull(nome)) {
            return this.nome;
        }
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        if (this.isNotNull(this.descricao)) {
            return this.descricao.toUpperCase();
        }
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private Boolean isNotNull(String valor) {
        return valor != null;
    }
}
