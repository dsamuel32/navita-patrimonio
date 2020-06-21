package br.com.navita.patrimonio.repository;

import br.com.navita.patrimonio.dominio.dto.MarcaDTO;
import br.com.navita.patrimonio.dominio.entidade.Marca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    @Query("SELECT " +
            "new br.com.navita.patrimonio.dominio.dto.MarcaDTO(m.id, m.nome) " +
            "FROM Marca m " +
            "WHERE 1 = 1 " +
            "AND (:nome IS NULL OR UPPER(m.nome) LIKE :nome) " +
            "AND (:id IS NULL OR m.id = :id) ")
    Page<MarcaDTO> recuperar(@Param("id") Long id, @Param("nome") String nome, Pageable pageable);

}
