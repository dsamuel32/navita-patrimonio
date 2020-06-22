package br.com.navita.patrimonio.dominio.builder;

import br.com.navita.patrimonio.dominio.entidade.Patrimonio;
import org.junit.Test;

import static org.junit.Assert.*;

public class PatrimonioBuilderTest {

    private final Long ID = 1L;
    private final String NOME_MARCA = "Dell";
    private final String NUMERO_TOMBO = "1234a";
    private final String NOME_PATRIMONIO = "Computador";
    private final String DESC_PATRINOMIO = "Computador de mesa";

    @Test
    public void build() {
        Patrimonio patrimonio = PatrimonioBuilder.getInstance()
                                                 .idMarca(ID)
                                                 .nomeMarca(NOME_MARCA)
                                                 .numeroTombo(NUMERO_TOMBO)
                                                 .nome(NOME_PATRIMONIO)
                                                 .descricao(DESC_PATRINOMIO)
                                                 .build();
        assertEquals(ID, patrimonio.getMarca().getId());
        assertEquals(NOME_MARCA, patrimonio.getMarca().getNome());
        assertEquals(NUMERO_TOMBO, patrimonio.getTombo());
        assertEquals(NOME_PATRIMONIO, patrimonio.getNome());
        assertEquals(DESC_PATRINOMIO, patrimonio.getDescricao());
    }

    @Test
    public void buildNumeroTomboNulo() {
        Patrimonio patrimonio = PatrimonioBuilder.getInstance()
                                                 .idMarca(ID)
                                                 .numeroTombo(null)
                                                 .nomeMarca(NOME_MARCA)
                                                 .nome(NOME_PATRIMONIO)
                                                 .descricao(DESC_PATRINOMIO)
                                                 .build();
        assertEquals(ID, patrimonio.getMarca().getId());
        assertEquals(NOME_MARCA, patrimonio.getMarca().getNome());
        assertNotNull(patrimonio.getTombo());
        assertEquals(NOME_PATRIMONIO, patrimonio.getNome());
        assertEquals(DESC_PATRINOMIO, patrimonio.getDescricao());
    }
}