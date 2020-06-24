package br.com.navita.patrimonio.service.impl;

import br.com.navita.patrimonio.dominio.builder.UsuarioDTOBuilder;
import br.com.navita.patrimonio.dominio.dto.*;
import br.com.navita.patrimonio.dominio.entidade.Permissao;
import br.com.navita.patrimonio.dominio.entidade.Usuario;
import br.com.navita.patrimonio.exception.ValidacaoSenhaException;
import br.com.navita.patrimonio.repository.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private Usuario usuario;

    @Mock
    private Permissao permissao;

    @Mock
    private Page<Usuario> page;

    @Mock
    private Pageable pageable;

    private final Long ID = 1L;
    private final String PERMISSAO = "ADM";
    private final String NOME = "TESTE";
    private final String EMAIL = "TESTE@TESTE.COM";
    private final String SENHA = "123";


    @Before
    public void setUp() {
        when(this.permissao.getId()).thenReturn(ID);
        when(this.permissao.getDescricao()).thenReturn(PERMISSAO);
        when(this.usuario.getId()).thenReturn(ID);
        when(this.usuario.getNome()).thenReturn(NOME);
        when(this.usuario.getEmail()).thenReturn(EMAIL);
        when(this.usuario.getPassword()).thenReturn(SENHA);
        when(this.usuario.getPermissoes()).thenReturn(new HashSet<>(Arrays.asList(this.permissao)));
        when(this.pageable.getPageNumber()).thenReturn(0);
        when(this.page.getPageable()).thenReturn(this.pageable);
        when(this.page.getTotalElements()).thenReturn(1L);
        when(this.page.getContent()).thenReturn(Arrays.asList(this.usuario));
        when(this.usuarioRepository.findByLogin(anyString())).thenReturn(this.usuario);
        when(this.usuarioRepository.findById(anyLong())).thenReturn(Optional.of(this.usuario));
        when(this.usuarioRepository.recuperar(anyLong(),anyString(), anyString(), any(Pageable.class))).thenReturn(this.page);
        when(this.usuarioRepository.save(any(Usuario.class))).thenReturn(this.usuario);

    }

    @Test
    public void loadUserByUsername() {
        Usuario usuario = (Usuario) this.usuarioService.loadUserByUsername(EMAIL);
        assertEquals(ID, usuario.getId());
        assertEquals(NOME, usuario.getNome());
        assertEquals(EMAIL, usuario.getEmail());
        usuario.getPermissoes().forEach(it -> {
            assertEquals(ID, it.getId());
            assertEquals(PERMISSAO, it.getDescricao());
        });
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsernameUsernameNotFoundException() {
        when(this.usuarioRepository.findByLogin(anyString())).thenReturn(null);
        this.usuarioService.loadUserByUsername(EMAIL);
    }

    @Test
    public void getUsuarioLogado() {
    }

    @Test
    public void recuperar() {
        FiltroUsuarioDTO filtroUsuarioDTO = new FiltroUsuarioDTO();
        filtroUsuarioDTO.setId(ID);
        filtroUsuarioDTO.setNome(NOME);
        filtroUsuarioDTO.setEmail(EMAIL);
        PaginacaoDTO<UsuarioDTO> paginacaoDTO = this.usuarioService.recuperar(filtroUsuarioDTO);
        assertEquals(new Integer(0), paginacaoDTO.getPagina());
        assertEquals(new Long(1), paginacaoDTO.getTotal());
        paginacaoDTO.getConteudo().forEach(it -> {
            assertEquals(ID, it.getId());
            assertEquals(NOME, it.getNome());
            assertEquals(EMAIL, it.getEmail());
        });
    }

    @Test
    public void salvar() {
        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.getInstance()
                                                 .id(ID)
                                                 .nome(NOME)
                                                 .email(EMAIL)
                                                 .permissoes(new HashSet<>(Arrays.asList(new Permissao(ID, PERMISSAO))))
                                                 .build();
        UsuarioDTO usuarioDTOSalvo = this.usuarioService.salvar(usuarioDTO);
        assertEquals(ID, usuarioDTOSalvo.getId());
        assertEquals(NOME, usuarioDTOSalvo.getNome());
        assertEquals(EMAIL, usuarioDTOSalvo.getEmail());
        usuarioDTOSalvo.getPermissoes().forEach(it -> {
            assertEquals(ID, it.getId());
            assertEquals(PERMISSAO, it.getDescricao());
        });

    }

    @Test
    public void alterar() {
        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.getInstance()
                                                 .id(ID)
                                                 .nome(NOME)
                                                 .email(EMAIL)
                                                 .build();
        UsuarioDTO usuarioDTOSalvo = this.usuarioService.alterar(usuarioDTO);
        assertEquals(ID, usuarioDTOSalvo.getId());
        assertEquals(NOME, usuarioDTOSalvo.getNome());
        assertEquals(EMAIL, usuarioDTOSalvo.getEmail());
        usuarioDTOSalvo.getPermissoes().forEach(it -> {
            assertEquals(ID, it.getId());
            assertEquals(PERMISSAO, it.getDescricao());
        });
    }

    @Test
    public void alterarSenha() {
        SenhaDTO senhaDTO = new SenhaDTO();
        senhaDTO.setSenhaAtual(SENHA);
        senhaDTO.setNovaSenha("123455");
        RespostaDTO respostaDTO = this.usuarioService.alterarSenha(ID, senhaDTO);
        assertEquals("Senha alterada com sucesso", respostaDTO.getMensagem());
    }

    @Test (expected = ValidacaoSenhaException.class)
    public void alterarSenhaDiferentes() {
        SenhaDTO senhaDTO = new SenhaDTO();
        senhaDTO.setSenhaAtual("3434");
        senhaDTO.setNovaSenha("123455");
        this.usuarioService.alterarSenha(ID, senhaDTO);
    }

    @Test
    public void desativar() {
        RespostaDTO respostaDTO = this.usuarioService.desativar(ID);
        assertEquals("Usuário desativado com sucesso.", respostaDTO.getMensagem());
    }
}