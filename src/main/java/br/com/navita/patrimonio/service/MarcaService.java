package br.com.navita.patrimonio.service;

import br.com.navita.patrimonio.dominio.dto.FiltroMarcaDTO;
import br.com.navita.patrimonio.dominio.dto.MarcaDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;

public interface MarcaService {

    MarcaDTO recuperarPorId(Long id);
    PaginacaoDTO<MarcaDTO> recuperar(FiltroMarcaDTO filtroMarcaDTO);
    MarcaDTO salvar(MarcaDTO marcaDTO);
    void apagar(Long id);

}
