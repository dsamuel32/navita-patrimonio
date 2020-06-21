package br.com.navita.patrimonio.dominio.entidade;

import javax.persistence.*;

@Entity
@Table(name = "patrimonio", schema = "patrimonio")
public class Patrimonio {

    @Id
    @Column(name = "numero_tombo")
    private String tombo;
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @OneToOne
    private Marca marca;

    public Patrimonio() { }

    public Patrimonio(String tombo, String nome, String descricao, Marca marca) {
        this.tombo = tombo;
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
