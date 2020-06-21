package br.com.navita.patrimonio.service.impl;

import br.com.navita.patrimonio.dominio.dto.FiltroPatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.MarcaDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.PatrimonioDTO;
import br.com.navita.patrimonio.dominio.entidade.Marca;
import br.com.navita.patrimonio.dominio.entidade.Patrimonio;
import br.com.navita.patrimonio.exception.CamposInvalidosException;
import br.com.navita.patrimonio.exception.NenhumResultadoEncontrado;
import br.com.navita.patrimonio.exception.ObjetoDublicadoException;
import br.com.navita.patrimonio.repository.PatrimonioRepository;
import br.com.navita.patrimonio.service.PatrimonioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import java.util.UUID;

@Service
public class PatrimonioServiceImpl implements PatrimonioService {

    @Autowired
    private PatrimonioRepository patrimonioRepository;

    @Override
    public PatrimonioDTO recuperarPorNumeroTombo(String tombo) {
        Patrimonio patrimonio = this.patrimonioRepository.findById(tombo).orElseThrow(NenhumResultadoEncontrado::new);
        Marca marca = patrimonio.getMarca();
        return new PatrimonioDTO(patrimonio.getTombo(),
                                 patrimonio.getNome(),
                                 patrimonio.getDescricao(),
                                 marca.getId(),
                                 marca.getNome());
    }

    @Override
    public PaginacaoDTO<PatrimonioDTO> recuperar(FiltroPatrimonioDTO filtroPatrimonioDTO) {
        Page<PatrimonioDTO> page = this.patrimonioRepository.recuperar(filtroPatrimonioDTO.getNumeroTombo(),
                                                                       filtroPatrimonioDTO.getNome(),
                                                                       filtroPatrimonioDTO.getDescricao(),
                                                                       filtroPatrimonioDTO.pageable());
        return new PaginacaoDTO<PatrimonioDTO>(page.getPageable().getPageNumber(),
                                               page.getTotalElements(),
                                               page.getContent());
    }

    @Override
    public PatrimonioDTO salvar(PatrimonioDTO patrimonioDTO) {

        String numeroTombo = patrimonioDTO.getNumeroTombo();
        if (patrimonioDTO.getNumeroTombo() == null) {
            numeroTombo = UUID.randomUUID().toString();
        }

        Patrimonio patrimonio = new Patrimonio(numeroTombo,
                                               patrimonioDTO.getNome(),
                                               patrimonioDTO.getDescricao(),
                                               new Marca(patrimonioDTO.getMarca().getId()));
        try {
            patrimonio = this.patrimonioRepository.save(patrimonio);
            Marca marca = patrimonio.getMarca();
            return new PatrimonioDTO(patrimonio.getTombo(),
                                     patrimonio.getNome(),
                                     patrimonio.getDescricao(),
                                     marca.getId(),
                                     marca.getNome());
        } catch (TransactionSystemException e) {
            throw new CamposInvalidosException(e);
        }
    }

    @Override
    public void apagar(String numeroTombo) {
        this.patrimonioRepository.deleteById(numeroTombo);
    }

}
