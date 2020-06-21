package br.com.navita.patrimonio.repository;

import br.com.navita.patrimonio.dominio.dto.MarcaDTO;
import br.com.navita.patrimonio.dominio.dto.PatrimonioDTO;
import br.com.navita.patrimonio.dominio.entidade.Patrimonio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, String> {

    @Query("SELECT " +
            "new br.com.navita.patrimonio.dominio.dto.PatrimonioDTO(p.tombo, p.nome, p.descricao, m.id, m.nome) " +
            "FROM Patrimonio p " +
            "JOIN Marca m on m.id = p.marca.id " +
            "WHERE 1 = 1 " +
            "AND (:nome IS NULL OR UPPER(p.nome) LIKE :nome) " +
            "AND (:tombo IS NULL OR p.tombo = :tombo)" +
            "AND (:descricao IS NULL OR UPPER(p.descricao) LIKE :descricao)")
    Page<PatrimonioDTO> recuperar(@Param("tombo") String tombo,
                             @Param("nome") String nome,
                             @Param("descricao") String descricao,
                             Pageable pageable);
}
