package br.com.navita.patrimonio.dominio.dto;

public class MarcaDTO {

    private Long id;
    private String nome;

    public MarcaDTO() { }

    public MarcaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        if (this.nome != null) {
            return this.nome.toUpperCase();
        }
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
