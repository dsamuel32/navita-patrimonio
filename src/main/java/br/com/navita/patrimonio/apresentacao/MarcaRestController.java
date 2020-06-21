package br.com.navita.patrimonio.apresentacao;

import br.com.navita.patrimonio.dominio.dto.FiltroMarcaDTO;
import br.com.navita.patrimonio.dominio.dto.MarcaDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = Paths.MARCAS, produces = MediaType.APPLICATION_JSON_VALUE)
public class MarcaRestController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PaginacaoDTO<MarcaDTO> listarMarcas(FiltroMarcaDTO filtroMarcaDTO) {
        return this.marcaService.recuperar(filtroMarcaDTO);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MarcaDTO recuperarPorId(@PathVariable("id") Long id) {
        return this.marcaService.recuperarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MarcaDTO salvar(@RequestBody MarcaDTO marcaDTO) {
        return this.marcaService.salvar(marcaDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public MarcaDTO atualizar(@RequestBody MarcaDTO marcaDTO) {
        return this.marcaService.salvar(marcaDTO);
    }

    @DeleteMapping("{id}")
    public void apagar(@PathVariable("id") Long id) {
        this.marcaService.apagar(id);
    }

}
