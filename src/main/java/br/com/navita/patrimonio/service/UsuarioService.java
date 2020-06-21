package br.com.navita.patrimonio.service;

import br.com.navita.patrimonio.dominio.dto.UsuarioDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    UsuarioDTO getUsuarioLogado();

}
