package br.edu.atitus.pooavancado.CadUsuario.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.edu.atitus.pooavancado.CadUsuario.Entities.Departamento;

@Repository
public interface DepartamentoRespository extends JpaRepository<Departamento, Long>{
	
	@Query(value = "update Departamento set status = not status where id = :ident", nativeQuery = true)
	@Modifying
	@Transactional
	void alteraStatusById(@Param("ident") long id);

	Page<Departamento> findByNomeContainingIgnoreCase(Pageable pageable, String nome);
	
	List<Departamento> findByEmailContainingIgnoreCase(String email);
	
	boolean existsByNomeAndIdNot(String nome, long id);
}