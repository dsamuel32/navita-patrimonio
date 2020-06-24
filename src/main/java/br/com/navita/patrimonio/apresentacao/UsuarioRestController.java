package br.com.navita.patrimonio.apresentacao;

import br.com.navita.patrimonio.dominio.dto.*;
import br.com.navita.patrimonio.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
        value = Paths.USUARIOS,
        description = "Patrimônios",
        consumes="application/json",
        produces="application/json"
)
@RestController
@RequestMapping(value = Paths.USUARIOS, produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation( value = "Recupera o usuário logado")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o usuário logado"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("logado")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO getUsuarioLogado() {
        return this.usuarioService.getUsuarioLogado();
    }

    @ApiOperation( value = "Recupera uma lista de usuários")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna uma listagem de usuários"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginacaoDTO<UsuarioDTO> recuperar(FiltroUsuarioDTO filtroUsuarioDTO) {
        return this.usuarioService.recuperar(filtroUsuarioDTO);
    }

    @ApiOperation( value = "Salva um usuário")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o usuário salvo"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO salvar(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.salvar(usuarioDTO);
    }

    @ApiOperation( value = "Atualiza um usuário")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o usuário atualizado"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO atualizar(@RequestBody UsuarioDTO usuarioDTO) {
        return this.usuarioService.alterar(usuarioDTO);
    }

    @ApiOperation( value = "Altera a senha dousuário")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna que a senha foi alterada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("{id}/senha")
    @ResponseStatus(HttpStatus.OK)
    public RespostaDTO atualizar(@PathVariable("id")Long id, @RequestBody SenhaDTO senhaDTO) {
        return this.usuarioService.alterarSenha(id, senhaDTO);
    }

    @ApiOperation( value = "Desativa um usuário")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o usuário foi desativado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping("desativar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RespostaDTO desativar(@PathVariable("id") Long id) {
        return this.usuarioService.desativar(id);
    }

}
