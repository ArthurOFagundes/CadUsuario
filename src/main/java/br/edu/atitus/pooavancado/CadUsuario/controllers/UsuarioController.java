package br.edu.atitus.pooavancado.CadUsuario.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Usuario;
import br.edu.atitus.pooavancado.CadUsuario.services.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {


	@Autowired
	private UsuarioService usuarioService;
	
	private ResponseEntity<Object> salvar(Usuario usuario) {
		try {
			usuarioService.save(usuario);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Object> postUsuario(@RequestBody Usuario usuario) {
		return salvar(usuario);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> putUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
		usuario.setId(id);
		return salvar(usuario);
	}

	@GetMapping()
	public ResponseEntity<Object> getUsuarios(@PageableDefault(page = 0, size = 5, sort = "id", direction = Direction.ASC) Pageable pageable, @RequestParam String nome) {
		Page<Usuario> usuarios = usuarioService.findByNome(pageable, nome);
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getUsuariobyId(@PathVariable long id) {
		Optional<Usuario> usuario = usuarioService.findById(id);
		if (usuario.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado com o Id " + id);
		else
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}

	

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUsuario(@PathVariable long id) {
		usuarioService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Usuário com Id " + id + " deletado com sucesso!");
	}

	@PatchMapping("/status/{id}")
	public ResponseEntity<Object> alteraStatusUsuario(@PathVariable long id) {
		try {
			usuarioService.alteraStatus(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Status alteado com sucesso para o usuario com Id " + id);
	}

}
