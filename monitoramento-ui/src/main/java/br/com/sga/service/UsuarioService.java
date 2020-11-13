package br.com.sga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sga.model.Funcao;
import br.com.sga.model.Usuario;
import br.com.sga.repository.FuncaoRepository;
import br.com.sga.repository.UsuarioRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private FuncaoRepository funcaoRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	/**
	 * @param ativo
	 * @return
	 */
	public Usuario save(Usuario usuario) {
		if (usuario.getCodigo() != null) {
			Optional<Usuario> a = usuarioRepository.findById(usuario.getCodigo());

			if (a.isPresent()) {
				throw new ServiceException("Usuario já existe na base.");
			}
		}
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuario = usuarioRepository.save(usuario);

		return usuario;
	}

	/**
	 * @param username
	 * @return
	 */
	public Usuario findByNome(String nome) {
		return usuarioRepository.findByNome(nome);
	}

	/**
	 * @return
	 */
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	/**
	 * @return
	 */
	public List<Funcao> listFuncoes() {
		return funcaoRepository.findAll();
	}

	/**
	 * @param id
	 */
	public void delete(Long id) {
		try {
			usuarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("The Usuario could not be found.");
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public Usuario findById(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new ServiceException("Usuario não encontrado.");
	}

	/**
	 * @param
	 */
	public void update(Usuario usuario) {
		findById(usuario.getCodigo());
		usuarioRepository.save(usuario);
	}
}