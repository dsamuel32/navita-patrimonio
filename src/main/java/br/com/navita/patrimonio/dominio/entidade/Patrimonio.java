package br.com.navita.patrimonio.dominio.entidade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "patrimonio", schema = "patrimonio")
public class Patrimonio {

    @Id
    @Column(name = "numero_tombo")
    private String tombo;
    @Column(name = "nome")
    @NotNull(message = "O campo nome não pode estar nulo")
    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String nome;
    @Column(name = "descricao")
    @NotNull(message = "O campo descrição não pode estar nulo")
    @NotEmpty(message = "O campo descrição não pode estar vazio")
    private String descricao;
    @OneToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    public Patrimonio() { }

    public Patrimonio(String tombo, String nome, String descricao, Marca marca) {
        this.tombo = tombo;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
    }

    public Patrimonio(String nome, String descricao, Marca marca) {
        this.tombo = UUID.randomUUID().toString();
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
    }

    public String getTombo() {
        return tombo;
    }

    public void setTombo(String tombo) {
        this.tombo = tombo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
