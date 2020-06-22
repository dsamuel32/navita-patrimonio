package br.com.navita.patrimonio.dominio.builder;

import br.com.navita.patrimonio.dominio.dto.PermissaoDTO;
import br.com.navita.patrimonio.dominio.dto.UsuarioDTO;
import br.com.navita.patrimonio.dominio.entidade.Permissao;
import br.com.navita.patrimonio.dominio.entidade.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class UsuarioDTOBuilderTest {

    private final String EMAIL = "teste@teste.com";
    private final String NOME = "teste";
    private final Long ID = 1L;
    private final String DESCRICAO_PERMISSAO = "ADM";
    private final Set<Permissao> permissoes = new HashSet<>();

    @Before
    public void setUp() {
        this.permissoes.add(new Permissao(ID, DESCRICAO_PERMISSAO));
    }


    @Test
    public void build() {
        UsuarioDTO usuario = UsuarioDTOBuilder.getInstance()
                                              .id(ID)
                                              .email(EMAIL)
                                              .nome(NOME)
                                              .permissoes(this.permissoes)
                                              .build();

        Set<PermissaoDTO> permissaoDTOS = usuario.getPermissoes();
        assertNotNull(usuario);
        assertEquals(ID, usuario.getId());
        assertEquals(NOME, usuario.getNome());
        assertEquals(EMAIL, usuario.getEmail());
        assertEquals(1, permissaoDTOS.size());
        permissaoDTOS.forEach(it -> {
            assertEquals(ID, it.getId());
            assertEquals(DESCRICAO_PERMISSAO, it.getDescricao());
        });


    }
}