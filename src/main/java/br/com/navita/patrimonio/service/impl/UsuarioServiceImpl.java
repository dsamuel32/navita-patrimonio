package br.com.navita.patrimonio.service.impl;

import br.com.navita.patrimonio.dominio.builder.UsuarioBuilder;
import br.com.navita.patrimonio.dominio.builder.UsuarioDTOBuilder;
import br.com.navita.patrimonio.dominio.dto.*;
import br.com.navita.patrimonio.dominio.entidade.Usuario;
import br.com.navita.patrimonio.exception.CamposInvalidosException;
import br.com.navita.patrimonio.exception.NenhumResultadoEncontrado;
import br.com.navita.patrimonio.exception.ObjetoDublicadoException;
import br.com.navita.patrimonio.exception.ValidacaoSenhaException;
import br.com.navita.patrimonio.repository.UsuarioRepository;
import br.com.navita.patrimonio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(email);
        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("Usuário não encontrado", email));
        }
        return usuario;
    }

    @Override
    public UsuarioDTO getUsuarioLogado() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = (String) authentication.getPrincipal();
        Usuario usuario = usuarioRepository.findByLogin(userName);
        UsuarioDTO usuarioDTO = UsuarioDTOBuilder.getInstance()
                                                 .id(usuario.getId())
                                                 .nome(usuario.getNome())
                                                 .email(usuario.getEmail())
                                                 .permissoes(usuario.getPermissoes())
                                                 .build();
        return usuarioDTO;
    }

    @Override
    public PaginacaoDTO<UsuarioDTO> recuperar(FiltroUsuarioDTO filtroUsuarioDTO) {
        Page<Usuario> page = this.usuarioRepository.recuperar(filtroUsuarioDTO.getId(),
                                                                   filtroUsuarioDTO.getNome(),
                                                                   filtroUsuarioDTO.getEmail(),
                                                                   filtroUsuarioDTO.pageable());
        List<UsuarioDTO> usuarios = new ArrayList<>();
        page.getContent().forEach(it -> {
            UsuarioDTO usuarioDTO = UsuarioDTOBuilder.getInstance()
                                                     .id(it.getId())
                                                     .nome(it.getNome())
                                                     .email(it.getEmail())
                                                     .permissoes(it.getPermissoes())
                                                     .build();
            usuarios.add(usuarioDTO);
        });
        return new PaginacaoDTO<>(page.getPageable().getPageNumber(), page.getTotalElements(), usuarios);
    }


    @Override
    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioBuilder.getInstance()
                                        .usuarioDTO(usuarioDTO)
                                        .permissoesDTO(usuarioDTO.getPermissoes())
                                        .build();
        return this.salvar(usuario);
    }

    private UsuarioDTO salvar(Usuario usuario) {
        try {
            Usuario usuarioSalvo = this.usuarioRepository.save(usuario);
            UsuarioDTO  usuarioDTO = UsuarioDTOBuilder.getInstance()
                                                      .id(usuarioSalvo.getId())
                                                      .nome(usuarioSalvo.getNome())
                                                      .email(usuarioSalvo.getEmail())
                                                      .permissoes(usuarioSalvo.getPermissoes())
                                                      .build();
            return usuarioDTO;
        } catch (TransactionSystemException | ConstraintViolationException e) {
            throw new CamposInvalidosException(e);
        } catch (DataIntegrityViolationException e) {
            throw new ObjetoDublicadoException("Já existe um registro com o e-mail " + usuario.getEmail());
        }

    }

    @Override
    public UsuarioDTO alterar(UsuarioDTO usuarioDTO) {
        Usuario usuario = this.usuarioRepository.findById(usuarioDTO.getId()).orElseThrow(NenhumResultadoEncontrado::new);
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNome(usuarioDTO.getNome());
        return this.salvar(usuario);
    }

    @Override
    public RespostaDTO alterarSenha(Long id, SenhaDTO senhaDTO) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(NenhumResultadoEncontrado::new);
        if (usuario.getPassword().equals(senhaDTO.getSenhaAtual())) {
            usuario.setPassword(senhaDTO.getNovaSenha());
            this.salvar(usuario);
            return new RespostaDTO("Senha alterada com sucesso");
        }
        throw new ValidacaoSenhaException();
    }

    @Override
    public RespostaDTO desativar(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(NenhumResultadoEncontrado::new);
        usuario.setEnabled(false);
        usuarioRepository.save(usuario);
        return new RespostaDTO("Usuário desativado com sucesso.");
    }
}
