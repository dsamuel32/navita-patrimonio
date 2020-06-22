package br.com.navita.patrimonio.service.impl;

import br.com.navita.patrimonio.dominio.dto.FiltroMarcaDTO;
import br.com.navita.patrimonio.dominio.dto.MarcaDTO;
import br.com.navita.patrimonio.dominio.dto.PaginacaoDTO;
import br.com.navita.patrimonio.dominio.dto.RespostaDTO;
import br.com.navita.patrimonio.dominio.entidade.Marca;
import br.com.navita.patrimonio.exception.CamposInvalidosException;
import br.com.navita.patrimonio.exception.NenhumResultadoEncontrado;
import br.com.navita.patrimonio.exception.ObjetoDublicadoException;
import br.com.navita.patrimonio.exception.RegistroNaoPodeSerApagadoException;
import br.com.navita.patrimonio.repository.MarcaRepository;
import br.com.navita.patrimonio.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public MarcaDTO recuperarPorId(Long id) {
        Marca marca = this.marcaRepository.findById(id).orElseThrow(NenhumResultadoEncontrado::new);
        return new MarcaDTO(marca.getId(), marca.getNome());
    }

    @Override
    public PaginacaoDTO<MarcaDTO> recuperar(FiltroMarcaDTO filtroMarcaDTO) {
        Page<MarcaDTO> page = this.marcaRepository.recuperar(filtroMarcaDTO.getId(), filtroMarcaDTO.getNome(), filtroMarcaDTO.pageable());
        return new PaginacaoDTO<>(page.getPageable().getPageNumber(), page.getTotalElements(), page.getContent());
    }

    @Override
    public MarcaDTO salvar(MarcaDTO marcaDTO) {
        Marca marca = new Marca(marcaDTO.getId(), marcaDTO.getNome());
        try {
            marca = this.marcaRepository.save(marca);
            return new MarcaDTO(marca.getId(), marca.getNome());
        } catch (TransactionSystemException e) {
            throw new CamposInvalidosException(e);
        } catch (DataIntegrityViolationException e) {
            throw new ObjetoDublicadoException("Já existe um registro com o nome " + marcaDTO.getNome());
        }

    }

    @Override
    public RespostaDTO apagar(Long id) {
        try {
            this.marcaRepository.deleteById(id);
            return new RespostaDTO("Marca apagada com sucesso.");
        } catch (DataIntegrityViolationException e) {
            throw new RegistroNaoPodeSerApagadoException("A marca não pode ser apagada");
        }

    }

}