package br.com.navita.patrimonio.dominio.dto;

import java.util.Set;

public class UsuarioDTO {

    private String nome;
    private String email;
    private Set<PermissaoDTO> permissoes;

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

    public Set<PermissaoDTO> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<PermissaoDTO> permissoes) {
        this.permissoes = permissoes;
    }
}
