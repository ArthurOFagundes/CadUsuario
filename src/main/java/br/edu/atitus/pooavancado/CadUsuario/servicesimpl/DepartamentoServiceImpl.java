package br.edu.atitus.pooavancado.CadUsuario.servicesimpl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Departamento;
import br.edu.atitus.pooavancado.CadUsuario.repositories.DepartamentoRespository;
import br.edu.atitus.pooavancado.CadUsuario.services.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	final DepartamentoRespository departamentoRepository;
	
	public DepartamentoServiceImpl(DepartamentoRespository departamentoRepository) {
		super();
		this.departamentoRepository = departamentoRepository;
	}
	
	private boolean existsByNomeAndIdNot(String nome, long id) {
		return departamentoRepository.existsByNomeAndIdNot(nome, id);
	}

	@Override
	public Departamento save(Departamento departamento) throws Exception {
		if (existsByNomeAndIdNot(departamento.getNome(), departamento.getId()))
			throw new Exception("Já existe departamento com este nome!");
		return departamentoRepository.save(departamento);
	}

	@Override
	public Page<Departamento> findByNome(Pageable pageable, String nome) {
		return departamentoRepository.findByNomeContainingIgnoreCase(pageable, nome);
	}

	@Override
	public Optional<Departamento> findById(long id) {
		return departamentoRepository.findById(id);
	}

	@Override
	public void deleteById(long id) {
		departamentoRepository.deleteById(id);;
	}


	

}
