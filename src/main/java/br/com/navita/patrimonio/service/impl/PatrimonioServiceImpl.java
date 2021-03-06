package br.com.navita.patrimonio.service.impl;

import br.com.navita.patrimonio.dominio.builder.PatrimonioBuilder;
import br.com.navita.patrimonio.dominio.dto.FiltroPatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.PatrimonioDTO;
import br.com.navita.patrimonio.dominio.dto.RespostaDTO;
import br.com.navita.patrimonio.dominio.entidade.Marca;
import br.com.navita.patrimonio.dominio.entidade.Patrimonio;
import br.com.navita.patrimonio.exception.CamposInvalidosException;
import br.com.navita.patrimonio.exception.NenhumResultadoEncontrado;
import br.com.navita.patrimonio.repository.PatrimonioRepository;
import br.com.navita.patrimonio.service.PatrimonioService;
import br.com.navita.patrimonio.validacao.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

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
        return new PaginacaoDTO<>(page.getPageable().getPageNumber(),
                                  page.getTotalElements(),
                                  page.getContent());
    }

    @Override
    public PatrimonioDTO salvar(PatrimonioDTO patrimonioDTO) {

        Validacao.newInstance().campoPreenchido("nome", patrimonioDTO.getNome())
                               .campoPreenchido("descricao", patrimonioDTO.getDescricao())
                               .campoPreenchido("marca", patrimonioDTO.getMarca().getId())
                               .validar();

        if (this.isNumeroTomboAlterado(patrimonioDTO.getNumeroTombo())) {
            throw new CamposInvalidosException("O campo número do tombo não pode ser alterado");
        }

        Patrimonio patrimonio = PatrimonioBuilder.getInstance()
                                                 .numeroTombo(patrimonioDTO.getNumeroTombo())
                                                 .nome(patrimonioDTO.getNome())
                                                 .descricao(patrimonioDTO.getDescricao())
                                                 .idMarca(patrimonioDTO.getMarca().getId())
                                                 .build();
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

    private Boolean isNumeroTomboAlterado(String numeroTombo) {
        if (numeroTombo == null) {
            return false;
        }
        return !this.patrimonioRepository.findById(numeroTombo).isPresent();
    }

    @Override
    public RespostaDTO apagar(String numeroTombo) {
        try {
            this.patrimonioRepository.deleteById(numeroTombo);
            return new RespostaDTO("Patrimonio apagado com sucesso.");
        } catch (EmptyResultDataAccessException e) {
            throw new NenhumResultadoEncontrado("Nenhum resultado encontrado para exclusão");
        }

    }

}
