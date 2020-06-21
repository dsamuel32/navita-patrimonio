package br.com.navita.patrimonio.apresentacao;

import br.com.navita.patrimonio.dominio.dto.UsuarioDTO;
import br.com.navita.patrimonio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Paths.USUARIOS, produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("logado")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO getUsuarioLogado() {
        return this.usuarioService.getUsuarioLogado();
    }

}
