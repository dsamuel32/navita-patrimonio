package br.com.navita.patrimonio.repository;

import br.com.navita.patrimonio.dominio.entidade.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.email =:email")
    Usuario findByLogin(@Param("email") String email);

    @Query("SELECT " +
            "u " +
            "FROM Usuario u " +
            "WHERE 1 = 1 " +
            "AND (:nome IS NULL OR UPPER(u.nome) LIKE :nome) " +
            "AND (:id IS NULL OR u.id = :id) " +
            "AND (:email IS NULL OR u.email = :email) ")
    Page<Usuario> recuperar(@Param("id") Long id,
                             @Param("nome") String nome,
                             @Param("email") String email,
                             Pageable pageable);

}
