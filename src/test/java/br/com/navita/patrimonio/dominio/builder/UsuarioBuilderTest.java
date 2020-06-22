package br.com.navita.patrimonio.dominio.builder;

import br.com.navita.patrimonio.dominio.dto.PermissaoDTO;
import br.com.navita.patrimonio.dominio.dto.UsuarioDTO;
import br.com.navita.patrimonio.dominio.entidade.Permissao;
import br.com.navita.patrimonio.dominio.entidade.Usuario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UsuarioBuilderTest {

    private final String EMAIL = "teste@teste.com";
    private final String NOME = "teste";
    private final Long ID = 1L;
    private final String DESCRICAO_PERMISSAO = "ADM";
    private final Set<PermissaoDTO> permissoes = new HashSet<>();
    private final UsuarioDTO usuarioDTO = new UsuarioDTO();

    @Before
    public void setUp() {
        this.permissoes.add(new PermissaoDTO(ID, DESCRICAO_PERMISSAO));
        this.usuarioDTO.setId(ID);
        this.usuarioDTO.setNome(NOME);
        this.usuarioDTO.setEmail(EMAIL);
    }


    @Test
    public void build() {
        Usuario usuario = UsuarioBuilder.getInstance()
                                        .permissoesDTO(this.permissoes)
                                        .usuarioDTO(this.usuarioDTO)
                                        .build();
        Set<Permissao> permissoes = usuario.getPermissoes();
        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(NOME, usuario.getNome());
        assertEquals(EMAIL, usuario.getEmail());
        assertEquals(1, permissoes.size());
        permissoes.forEach(it -> {
            assertEquals(ID, it.getId());
            assertEquals(DESCRICAO_PERMISSAO, it.getDescricao());
        });


    }
}