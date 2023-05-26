package br.edu.atitus.pooavancado.CadUsuario.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Usuario;

public interface UsuarioService {
	
	Usuario save(Usuario usuario) throws Exception;
	
	Page<Usuario> findByNome(Pageable pageable, String nome);
	
	Optional<Usuario> findById(long id);
	
	void deleteById(long id);
	
	void alteraStatus(long id) throws Exception;

}
