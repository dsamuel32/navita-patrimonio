package br.com.navita.patrimonio.service.impl;

import br.com.navita.patrimonio.dominio.dto.*;
import br.com.navita.patrimonio.dominio.entidade.Marca;
import br.com.navita.patrimonio.dominio.entidade.Patrimonio;
import br.com.navita.patrimonio.exception.CamposInvalidosException;
import br.com.navita.patrimonio.exception.NenhumResultadoEncontrado;
import br.com.navita.patrimonio.exception.RegistroNaoPodeSerApagadoException;
import br.com.navita.patrimonio.repository.PatrimonioRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.TransactionSystemException;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatrimonioServiceImplTest {

    @InjectMocks
    private PatrimonioServiceImpl patrimonioService;

    @Mock
    private PatrimonioRepository patrimonioRepository;

    @Mock
    private Patrimonio patrimonio;

    @Mock
    private Marca marca;

    @Mock
    private Page<PatrimonioDTO> page;

    private final Long ID = 1L;
    private final String NUMERO_TOMBO = "ABC12";
    private final String DESC_MARCA = "DELL";
    private final String NOME_PATRIMONIO = "COMPUTADOR";
    private final String DES_PATRIMONIO = "COMPUTADOR";

    @Before
    public void setUp() {
        when(this.pageable.getPageNumber()).thenReturn(0);
        when(this.page.getPageable()).thenReturn(this.pageable);
        when(this.page.getTotalElements()).thenReturn(1L);
        when(this.page.getContent()).thenReturn(Arrays.asList(new PatrimonioDTO(NUMERO_TOMBO, NOME_PATRIMONIO, DES_PATRIMONIO, ID, DES_PATRIMONIO)));
        when(this.marca.getId()).thenReturn(ID);
        when(this.marca.getNome()).thenReturn(DESC_MARCA);
        when(this.patrimonio.getTombo()).thenReturn(NUMERO_TOMBO);
        when(this.patrimonio.getNome()).thenReturn(NOME_PATRIMONIO);
        when(this.patrimonio.getDescricao()).thenReturn(DES_PATRIMONIO);
        when(this.patrimonio.getMarca()).thenReturn(this.marca);
        when(this.patrimonioRepository.findById(anyString())).thenReturn(Optional.of(this.patrimonio));
        when(this.patrimonioRepository.save(any(Patrimonio.class))).thenReturn(this.patrimonio);
        when(this.patrimonioRepository.recuperar(anyString(), anyString(), anyString(), any(Pageable.class))).thenReturn(this.page);
    }

    @Mock
    private Pageable pageable;

    @Test
    public void recuperarPorNumeroTombo() {
        PatrimonioDTO patrimonioDTO = this.patrimonioService.recuperarPorNumeroTombo("123");
        assertEquals(NUMERO_TOMBO, patrimonioDTO.getNumeroTombo());
        assertEquals(NOME_PATRIMONIO, patrimonioDTO.getNome());
        assertEquals(DES_PATRIMONIO, patrimonioDTO.getDescricao());
    }

    @Test(expected = NenhumResultadoEncontrado.class)
    public void recuperarPorNumeroTomboNenhumResultadoEncontrado() {
        when(this.patrimonioRepository.findById(anyString())).thenThrow(NenhumResultadoEncontrado.class);
        this.patrimonioService.recuperarPorNumeroTombo("123");
    }

    @Test
    public void recuperar() {
        FiltroPatrimonioDTO filtroPatrimonioDTO = new FiltroPatrimonioDTO();
        filtroPatrimonioDTO.setNome(NOME_PATRIMONIO);
        filtroPatrimonioDTO.setDescricao(DES_PATRIMONIO);
        filtroPatrimonioDTO.setNumeroTombo(NUMERO_TOMBO);
        PaginacaoDTO<PatrimonioDTO> paginacaoDTO = this.patrimonioService.recuperar(filtroPatrimonioDTO);

        assertEquals(new Integer(0), paginacaoDTO.getPagina());
        assertEquals(new Long(1), paginacaoDTO.getTotal());
        paginacaoDTO.getConteudo().forEach(it -> {
            assertEquals(NUMERO_TOMBO, it.getNumeroTombo());
            assertEquals(NOME_PATRIMONIO, it.getNome());
            assertEquals(DES_PATRIMONIO, it.getDescricao());
        });
    }

    @Test
    public void salvar() {
        PatrimonioDTO patrimonioDTO = this.patrimonioService.salvar(new PatrimonioDTO(NUMERO_TOMBO, NOME_PATRIMONIO, DES_PATRIMONIO, ID, DES_PATRIMONIO));
        assertEquals(NUMERO_TOMBO, patrimonioDTO.getNumeroTombo());
        assertEquals(NOME_PATRIMONIO, patrimonioDTO.getNome());
        assertEquals(DES_PATRIMONIO, patrimonioDTO.getDescricao());
    }

    @Test
    public void apagar() {
        RespostaDTO respostaDTO = this.patrimonioService.apagar(NUMERO_TOMBO);
        assertEquals("Patrimonio apagado com sucesso.", respostaDTO.getMensagem());
    }

}