package br.com.navita.patrimonio.service;

import br.com.navita.patrimonio.dominio.dto.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    UsuarioDTO getUsuarioLogado();
    PaginacaoDTO<UsuarioDTO> recuperar(FiltroUsuarioDTO filtroUsuarioDTO);
    UsuarioDTO salvar(UsuarioDTO usuarioDTO);
    RespostaDTO desativar(Long id);

}
