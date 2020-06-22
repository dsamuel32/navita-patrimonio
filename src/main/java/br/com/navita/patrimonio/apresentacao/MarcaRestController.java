package br.com.navita.patrimonio.apresentacao;

import br.com.navita.patrimonio.dominio.dto.FiltroMarcaDTO;
import br.com.navita.patrimonio.dominio.dto.MarcaDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.RespostaDTO;
import br.com.navita.patrimonio.service.MarcaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(
        value = Paths.MARCAS,
        description = "Marcas",
        consumes="application/json",
        produces="application/json"
)
@RestController
@RequestMapping(value = Paths.MARCAS, produces = MediaType.APPLICATION_JSON_VALUE)
public class MarcaRestController {

    @Autowired
    private MarcaService marcaService;

    @ApiOperation( value = "Recupera uma lista de marcas")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna uma listagem de marcas"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginacaoDTO<MarcaDTO> recuperar(FiltroMarcaDTO filtroMarcaDTO) {
        return this.marcaService.recuperar(filtroMarcaDTO);
    }

    @ApiOperation( value = "Recupera uma marca pelo seu id")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna uma marca"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MarcaDTO recuperarPorId(@PathVariable("id") Long id) {
        return this.marcaService.recuperarPorId(id);
    }

    @ApiOperation( value = "Salva uma marca")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna a marca salva"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MarcaDTO salvar(@RequestBody MarcaDTO marcaDTO) {
        return this.marcaService.salvar(marcaDTO);
    }

    @ApiOperation( value = "Atualiza uma marca")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna a marca atualizada"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MarcaDTO atualizar(@RequestBody MarcaDTO marcaDTO) {
        return this.marcaService.salvar(marcaDTO);
    }

    @ApiOperation( value = "Apaga uma marca")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = "Retorna que a marca foi apagada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição Inválida"),
            @ApiResponse(code = 401, message = "Usuário não autenticado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @DeleteMapping("{id}")
    public RespostaDTO apagar(@PathVariable("id") Long id) {
        return this.marcaService.apagar(id);
    }

}
