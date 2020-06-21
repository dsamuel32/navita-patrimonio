package br.com.navita.patrimonio.apresentacao;

import br.com.navita.patrimonio.dominio.dto.FiltroPatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.PatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.RespostaDTO;
import br.com.navita.patrimonio.service.PatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Paths.PATRIMONIOS, produces = MediaType.APPLICATION_JSON_VALUE)
public class PatrimoniosRestController {

    @Autowired
    private PatrimonioService patrimonioService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginacaoDTO<PatrimonioDTO> listarMarcas(FiltroPatrimonioDTO filtroPatrimonioDTO) {
        return this.patrimonioService.recuperar(filtroPatrimonioDTO);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatrimonioDTO recuperarPorNumeroTombo(@PathVariable("id") String id) {
        return this.patrimonioService.recuperarPorNumeroTombo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatrimonioDTO salvar(@RequestBody PatrimonioDTO patrimonioDTO) {
        return this.patrimonioService.salvar(patrimonioDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public PatrimonioDTO atualizar(@RequestBody PatrimonioDTO patrimonioDTO) {
        return this.patrimonioService.salvar(patrimonioDTO);
    }

    @DeleteMapping("{id}")
    public RespostaDTO apagar(@PathVariable("id") String id) {
        return this.patrimonioService.apagar(id);
    }
}
