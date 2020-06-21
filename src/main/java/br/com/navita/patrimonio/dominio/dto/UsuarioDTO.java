package br.com.navita.patrimonio.dominio.dto;

import java.util.Set;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Set<PermissaoDTO> permissoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<PermissaoDTO> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<PermissaoDTO> permissoes) {
        this.permissoes = permissoes;
    }
}
