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
import br.com.navita.patrimonio.validacao.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        Validacao.newInstance().campoPreenchido("nome", marcaDTO.getNome()).validar();
        Marca marca = new Marca(marcaDTO.getId(), marcaDTO.getNome());
        return salvar(marca);
    }

    @Override
    public MarcaDTO alterar(MarcaDTO marcaDTO) {
        Validacao.newInstance()
                 .campoPreenchido("id", marcaDTO.getId())
                 .campoPreenchido("nome", marcaDTO.getNome())
                 .validar();
        Marca marca = new Marca(marcaDTO.getId(), marcaDTO.getNome());
        return salvar(marca);
    }

    private MarcaDTO salvar(Marca marca) {
        try {
            Marca marcaSalva = this.marcaRepository.save(marca);
            return new MarcaDTO(marcaSalva.getId(), marcaSalva.getNome());
        } catch (TransactionSystemException e) {
            throw new CamposInvalidosException(e);
        } catch (DataIntegrityViolationException e) {
            throw new ObjetoDublicadoException("Já existe uma marca com o nome " + marca.getNome());
        }
    }

    @Override
    public RespostaDTO apagar(Long id) {
        try {
            this.marcaRepository.deleteById(id);
            return new RespostaDTO("Marca apagada com sucesso.");
        } catch (DataIntegrityViolationException e) {
            throw new RegistroNaoPodeSerApagadoException("A marca não pode ser apagada");
        } catch (EmptyResultDataAccessException e) {
            throw new NenhumResultadoEncontrado("Nenhum resultado encontrado para exclusão");
        }

    }

}
