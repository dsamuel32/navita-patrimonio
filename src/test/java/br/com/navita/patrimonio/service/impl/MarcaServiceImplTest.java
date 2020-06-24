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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarcaServiceImplTest {

    @InjectMocks
    private MarcaServiceImpl marcaService;

    @Mock
    private MarcaRepository marcaRepository;

    @Mock
    private Marca marca;

    @Mock
    private Page<MarcaDTO> page;

    @Mock
    private Pageable pageable;

    @Mock
    private TransactionSystemException transactionSystemException;

    @Mock
    private ConstraintViolationException constraintViolationException;

    @Mock
    private ConstraintViolation<String> constraintViolation;

    private static Long ID = 1L;
    private static String MARCA = "DELL";

    @Before
    public void setUp() {
        when(this.pageable.getPageNumber()).thenReturn(0);
        when(this.page.getPageable()).thenReturn(this.pageable);
        when(this.page.getTotalElements()).thenReturn(1L);
        when(this.page.getContent()).thenReturn(Arrays.asList(new MarcaDTO(ID, MARCA)));
        when(this.marca.getId()).thenReturn(ID);
        when(this.marca.getNome()).thenReturn(MARCA);
        when(this.marcaRepository.findById(anyLong())).thenReturn(Optional.of(this.marca));
        when(this.marcaRepository.recuperar(anyLong(), anyString(), any(Pageable.class))).thenReturn(this.page);
    }


    @Test
    public void recuperarPorId() {
        MarcaDTO marcaDTO = this.marcaService.recuperarPorId(1L);
        assertEquals(ID, marcaDTO.getId());
        assertEquals(MARCA, marcaDTO.getNome());
    }

    @Test(expected = NenhumResultadoEncontrado.class)
    public void recuperarPorIdNenhumResultadoEncontrado() {
        when(this.marcaRepository.findById(anyLong())).thenThrow(NenhumResultadoEncontrado.class);
        this.marcaService.recuperarPorId(1L);
    }

    @Test
    public void recuperar() {
        FiltroMarcaDTO filtroMarcaDTO = new FiltroMarcaDTO();
        filtroMarcaDTO.setId(ID);
        filtroMarcaDTO.setNome(MARCA);
        PaginacaoDTO<MarcaDTO> paginacaoDTO = this.marcaService.recuperar(filtroMarcaDTO);
        assertEquals(new Integer(0), paginacaoDTO.getPagina());
        assertEquals(new Long(1), paginacaoDTO.getTotal());
        paginacaoDTO.getConteudo().forEach(it -> {
            assertEquals(ID, it.getId());
            assertEquals(MARCA, it.getNome());
        });
    }

    @Test
    public void salvar() {
        when(this.marcaRepository.save(any(Marca.class))).thenReturn(this.marca);
        MarcaDTO marcaDTO = this.marcaService.salvar(new MarcaDTO(null, MARCA));
        assertEquals(ID, marcaDTO.getId());
        assertEquals(MARCA, marcaDTO.getNome());
    }

    @Test(expected = CamposInvalidosException.class)
    public void salvarParametrosInvalidos() {
        this.marcaService.salvar(new MarcaDTO(null, null));
    }

    @Test
    public void alterar() {
        when(this.marcaRepository.save(any(Marca.class))).thenReturn(this.marca);
        MarcaDTO marcaDTO = this.marcaService.salvar(new MarcaDTO(ID, MARCA));
        assertEquals(ID, marcaDTO.getId());
        assertEquals(MARCA, marcaDTO.getNome());
    }

    @Test(expected = ObjetoDublicadoException.class)
    public void salvarObjetoDublicadoException() {
        when(this.marcaRepository.save(any(Marca.class))).thenThrow(DataIntegrityViolationException.class);
        this.marcaService.salvar(new MarcaDTO(null, MARCA));
    }

    @Test
    public void apagar() {
        RespostaDTO respostaDTO = this.marcaService.apagar(1L);
        assertEquals("Marca apagada com sucesso.", respostaDTO.getMensagem());
    }

    @Test(expected = RegistroNaoPodeSerApagadoException.class)
    public void apagarRegistroNaoPodeSerApagadoException() {
        doThrow(DataIntegrityViolationException.class).when(this.marcaRepository).deleteById(anyLong());
        this.marcaService.apagar(1L);
    }

    @Test(expected = NenhumResultadoEncontrado.class)
    public void apagarNenhumResultadoEncontrado() {
        doThrow(NenhumResultadoEncontrado.class).when(this.marcaRepository).deleteById(anyLong());
        this.marcaService.apagar(1L);
    }
}