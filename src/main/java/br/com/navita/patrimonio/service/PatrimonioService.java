package br.com.navita.patrimonio.service;

import br.com.navita.patrimonio.dominio.dto.FiltroPatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.PatrimonioDTO;

public interface PatrimonioService {

    PatrimonioDTO recuperarPorNumeroTombo(String tombo);
    PaginacaoDTO<PatrimonioDTO> recuperar(FiltroPatrimonioDTO filtroPatrimonioDTO);
    PatrimonioDTO salvar(PatrimonioDTO patrimonioDTO);
    void apagar(String numeroTombo);

}
