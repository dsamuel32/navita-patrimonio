package br.com.navita.patrimonio.apresentacao;

import br.com.navita.patrimonio.dominio.dto.*;
import br.com.navita.patrimonio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginacaoDTO<UsuarioDTO> recuperar(FiltroUsuarioDTO filtroUsuarioDTO) {
        return this.usuarioService.recuperar(filtroUsuarioDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.salvar(usuarioDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO atualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.salvar(usuarioDTO);
    }

    @PutMapping("desativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespostaDTO desativar(@PathVariable("id") Long id) {
        return this.usuarioService.desativar(id);
    }

}
