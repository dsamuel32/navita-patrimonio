package br.com.navita.patrimonio.dominio.entidade;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "marca", schema = "patrimonio")
public class Marca {

    @Id
    @SequenceGenerator(allocationSize = 1, initialValue = 1, sequenceName = "patrimonio.seq_marca", name = "seq_marca")
    @GeneratedValue(generator = "seq_marca", strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome")
    @NotNull(message = "O campo nome não pode estar nulo")
    @NotEmpty(message = "O campo nome não pode estar vazio")
    private String nome;

    public Marca() { }

    public Marca(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Marca(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
