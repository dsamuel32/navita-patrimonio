package br.com.navita.patrimonio.service.impl;

import br.com.navita.patrimonio.dominio.builder.UsuarioDTOBuilder;
import br.com.navita.patrimonio.dominio.dto.UsuarioDTO;
import br.com.navita.patrimonio.dominio.entidade.Usuario;
import br.com.navita.patrimonio.repository.UsuarioRepository;
import br.com.navita.patrimonio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
                                                 .nome(usuario.getNome())
                                                 .email(usuario.getEmail())
                                                 .permissoes(usuario.getPermissoes())
                                                 .build();
        return usuarioDTO;
    }
}
