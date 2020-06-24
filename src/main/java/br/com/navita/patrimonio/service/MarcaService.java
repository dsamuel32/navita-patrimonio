package br.com.navita.patrimonio.service;

import br.com.navita.patrimonio.dominio.dto.FiltroMarcaDTO;
import br.com.navita.patrimonio.dominio.dto.MarcaDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.RespostaDTO;

public interface MarcaService {

    MarcaDTO recuperarPorId(Long id);
    PaginacaoDTO<MarcaDTO> recuperar(FiltroMarcaDTO filtroMarcaDTO);
    MarcaDTO salvar(MarcaDTO marcaDTO);
    MarcaDTO alterar(MarcaDTO marcaDTO);
    RespostaDTO apagar(Long id);

}
