package br.com.navita.patrimonio.dominio.builder;

import br.com.navita.patrimonio.dominio.dto.PermissaoDTO;
import br.com.navita.patrimonio.dominio.dto.UsuarioDTO;
import br.com.navita.patrimonio.dominio.entidade.Permissao;
import br.com.navita.patrimonio.dominio.entidade.Usuario;

import java.util.HashSet;
import java.util.Set;

public class UsuarioBuilder {

    private Usuario usuario;
    private Set<Permissao> permissoes;

    private UsuarioBuilder () {
        this.usuario = new Usuario();
        this.permissoes = new HashSet<>();
    }

    public static UsuarioBuilder newnstance() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder usuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuario.setId(usuarioDTO.getId());
        this.usuario.setNome(usuarioDTO.getNome());
        this.usuario.setPassword(usuarioDTO.getSenha());
        this.usuario.setEmail(usuarioDTO.getEmail());
        return this;
    }

    public UsuarioBuilder permissao(Long id) {
        this.permissoes.add(new Permissao(id, null));
        this.usuario.setPermissoes(this.permissoes);
        return this;
    }

    public UsuarioBuilder permissoesDTO(Set<PermissaoDTO> permissoes) {
        if (permissoes != null) {
            permissoes.forEach(it -> this.permissoes.add(new Permissao(it.getId(), it.getDescricao())));
            this.usuario.setPermissoes(this.permissoes);
        }
        return this;
    }

    public Usuario build() {
        return this.usuario;
    }
}
