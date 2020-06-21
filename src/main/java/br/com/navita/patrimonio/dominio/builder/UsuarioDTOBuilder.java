package br.com.navita.patrimonio.dominio.builder;

import br.com.navita.patrimonio.dominio.dto.PermissaoDTO;
import br.com.navita.patrimonio.dominio.dto.UsuarioDTO;
import br.com.navita.patrimonio.dominio.entidade.Permissao;

import java.util.HashSet;
import java.util.Set;

public class UsuarioDTOBuilder {

    private UsuarioDTO usuarioDTO;
    private Set<PermissaoDTO> permissoes;
    private static UsuarioDTOBuilder builder;

    private UsuarioDTOBuilder() {
        this.usuarioDTO = new UsuarioDTO();
        this.permissoes = new HashSet<>();
    }

    public static UsuarioDTOBuilder getInstance() {
        if (builder == null) {
            builder = new UsuarioDTOBuilder();
        }
        return builder;
    }

    public UsuarioDTOBuilder nome(String nome) {
        this.usuarioDTO.setNome(nome);
        return this;
    }

    public UsuarioDTOBuilder email(String email) {
        this.usuarioDTO.setEmail(email);
        return this;
    }

    public UsuarioDTOBuilder permissoes(Set<Permissao> permisoes) {
        permisoes.forEach(it -> this.permissoes.add(new PermissaoDTO(it.getId(), it.getDescricao())));
        this.usuarioDTO.setPermissoes(this.permissoes);
        return this;
    }

    public UsuarioDTO build() {
        return this.usuarioDTO;
    }

}
