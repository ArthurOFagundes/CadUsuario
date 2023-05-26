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

import br.edu.atitus.pooavancado.CadUsuario.Entities.Departamento;
import br.edu.atitus.pooavancado.CadUsuario.services.DepartamentoService;
import br.edu.atitus.pooavancado.CadUsuario.services.GenericService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/departamentos")
public class DepartamentoController extends GenericController<Departamento>{

	final DepartamentoService departamentoService;
	public DepartamentoController(DepartamentoService departamentoService) {
		super();
		this.departamentoService = departamentoService;
	}
	@Override
	GenericService<Departamento> getService() {
		return departamentoService;
	}


}