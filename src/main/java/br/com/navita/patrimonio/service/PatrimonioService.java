package br.com.navita.patrimonio.service;

import br.com.navita.patrimonio.dominio.dto.FiltroPatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.PatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.RespostaDTO;

public interface PatrimonioService {

    PatrimonioDTO recuperarPorNumeroTombo(String tombo);
    PaginacaoDTO<PatrimonioDTO> recuperar(FiltroPatrimonioDTO filtroPatrimonioDTO);
    PatrimonioDTO salvar(PatrimonioDTO patrimonioDTO);
    RespostaDTO apagar(String numeroTombo);

}
