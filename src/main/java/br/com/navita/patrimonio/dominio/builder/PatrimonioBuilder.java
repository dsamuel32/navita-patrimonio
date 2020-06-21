package br.com.navita.patrimonio.dominio.builder;

import br.com.navita.patrimonio.dominio.entidade.Marca;
import br.com.navita.patrimonio.dominio.entidade.Patrimonio;

import java.util.UUID;

public class PatrimonioBuilder {

    private Patrimonio patrimonio;
    private Marca marca;

    private PatrimonioBuilder() {
        this.patrimonio = new Patrimonio();
        this.marca = new Marca();
    }
    public static PatrimonioBuilder getInstance() {
        return new PatrimonioBuilder();
    }


    public PatrimonioBuilder numeroTombo(String id) {
        if (id == null) {
            this.patrimonio.setTombo(UUID.randomUUID().toString());
        } else {
            this.patrimonio.setTombo(id);
        }
        return this;

    }
    public PatrimonioBuilder nome(String nome) {
        this.patrimonio.setNome(nome);
        return this;
    }

    public PatrimonioBuilder descricao(String descricao) {
        this.patrimonio.setDescricao(descricao);
        return this;
    }

    public PatrimonioBuilder idMarca(Long id) {
        this.marca.setId(id);
        return this;
    }

    public PatrimonioBuilder nomeMarca(String nome) {
        this.marca.setNome(nome);
        return this;
    }

    public Patrimonio build() {
        this.patrimonio.setMarca(this.marca);
        return this.patrimonio;
    }

}
