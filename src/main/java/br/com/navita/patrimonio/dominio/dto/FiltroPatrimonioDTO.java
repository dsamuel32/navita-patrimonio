package br.com.navita.patrimonio.dominio.dto;

public class FiltroPatrimonioDTO extends FiltroPaginacaoDTO {

    private String numeroTombo;
    private String nome;
    private String descricao;

    public String getNumeroTombo() {
        if (this.isNotNull(this.numeroTombo)) {
            return this.numeroTombo;
        }
        return numeroTombo;
    }

    public void setNumeroTombo(String numeroTombo) {
        this.numeroTombo = numeroTombo;
    }

    public String getNome() {
        return this.upperCase(this.nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.upperCase(this.descricao);
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
