package br.edu.atitus.pooavancado.CadUsuario.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Departamento;

public interface DepartamentoService {
	
	Departamento save(Departamento departamento) throws Exception;
	
	Page<Departamento> findByNome(Pageable pageable, String nome);
	
	Optional<Departamento> findById(long id);
	
	void deleteById(long id);


}
