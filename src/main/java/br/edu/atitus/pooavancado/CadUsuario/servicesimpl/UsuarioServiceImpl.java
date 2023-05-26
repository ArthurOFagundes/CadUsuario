package br.edu.atitus.pooavancado.CadUsuario.servicesimpl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.atitus.pooavancado.CadUsuario.Entities.Usuario;
import br.edu.atitus.pooavancado.CadUsuario.repositories.UsuarioRespository;
import br.edu.atitus.pooavancado.CadUsuario.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	final UsuarioRespository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRespository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}
	
	private boolean existsByNomeAndIdNot(String nome, long id) {
		return usuarioRepository.existsByNomeAndIdNot(nome, id);
	}

	@Override
	public Usuario save(Usuario usuario) throws Exception {
		if (existsByNomeAndIdNot(usuario.getNome(), usuario.getId()))
			throw new Exception("Já existe usuário com este nome!");
		return usuarioRepository.save(usuario);
	}

	@Override
	public Page<Usuario> findByNome(Pageable pageable, String nome) {
		return usuarioRepository.findByNomeContainingIgnoreCase(pageable, nome);
	}

	@Override
	public Optional<Usuario> findById(long id) {
		return usuarioRepository.findById(id);
	}

	@Override
	public void deleteById(long id) {
		usuarioRepository.deleteById(id);;
	}

	@Override
	public void alteraStatus(long id) throws Exception {
		if (!usuarioRepository.existsById(id))
			throw new Exception("Não existe usuário com este Id");
		usuarioRepository.alteraStatusById(id);
	}
	

}
