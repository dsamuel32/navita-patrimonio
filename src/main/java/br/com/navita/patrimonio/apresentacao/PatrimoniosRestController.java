package br.com.navita.patrimonio.apresentacao;

import br.com.navita.patrimonio.dominio.dto.FiltroPatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.PatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.RespostaDTO;
import br.com.navita.patrimonio.service.PatrimonioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
        value = Paths.PATRIMONIOS,
        description = "Patrimônios",
        consumes="application/json",
        produces="application/json"
)
@RestController
@RequestMapping(value = Paths.PATRIMONIOS, produces = MediaType.APPLICATION_JSON_VALUE)
public class PatrimoniosRestController {

    @Autowired
    private PatrimonioService patrimonioService;

    @ApiOperation( value = "Recupera uma lista de patrimonios")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna uma listagem de patrimônios"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginacaoDTO<PatrimonioDTO> recuperar(FiltroPatrimonioDTO filtroPatrimonioDTO) {
        return this.patrimonioService.recuperar(filtroPatrimonioDTO);
    }

    @ApiOperation( value = "Recupera um patrimônio")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna um patrimônio"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatrimonioDTO recuperarPorNumeroTombo(@PathVariable("id") String id) {
        return this.patrimonioService.recuperarPorNumeroTombo(id);
    }

    @ApiOperation( value = "Salva um patrimônio")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o patrimônio salvo"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatrimonioDTO salvar(@RequestBody PatrimonioDTO patrimonioDTO) {
        return this.patrimonioService.salvar(patrimonioDTO);
    }

    @ApiOperation( value = "Atualiza um patrimônio")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna o patrimônio atualizado"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PatrimonioDTO atualizar(@RequestBody PatrimonioDTO patrimonioDTO) {
        return this.patrimonioService.salvar(patrimonioDTO);
    }

    @ApiOperation( value = "Apaga um patrimônio")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna que o patrimônio foi apagado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("{id}")
    public RespostaDTO apagar(@PathVariable("id") String id) {
        return this.patrimonioService.apagar(id);
    }

}
