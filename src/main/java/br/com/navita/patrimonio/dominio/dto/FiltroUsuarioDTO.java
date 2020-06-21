package br.com.navita.patrimonio.dominio.dto;

public class FiltroUsuarioDTO extends FiltroPaginacaoDTO {

    private Long id;
    private String nome;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.upperCase(this.nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.upperCase(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
