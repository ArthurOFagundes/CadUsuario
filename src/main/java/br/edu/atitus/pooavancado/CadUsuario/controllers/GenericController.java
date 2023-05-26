package br.edu.atitus.pooavancado.CadUsuario.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.atitus.pooavancado.CadUsuario.Entities.GenericEntity;
import br.edu.atitus.pooavancado.CadUsuario.services.GenericService;

public abstract class GenericController<TEntidade extends GenericEntity> {
	
	abstract GenericService<TEntidade> getService();

	private ResponseEntity<Object> salvar(TEntidade entidade) {
		try {
			getService().save(entidade);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}
	
	@PostMapping
	public ResponseEntity<Object> postUsuario(@RequestBody TEntidade entidade) {
		return salvar(entidade);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putUsuario(@PathVariable long id, @RequestBody TEntidade entidade) {
		entidade.setId(id);
		return salvar(entidade);
	}

	@GetMapping()
	public ResponseEntity<Object> getUsuarios(@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam String nome) {
		Page<TEntidade> usuarios = getService().findByNome(pageable, nome);
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUsuariobyId(@PathVariable long id) throws Exception {
		Optional<TEntidade> entidade = getService().findById(id);
		if (entidade.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("TEntidade não encontrado com o Id " + id);
		else
			return ResponseEntity.status(HttpStatus.OK).body(entidade);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable long id) {
		getService().deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuário com Id " + id + " deletado com sucesso!");
	}

}