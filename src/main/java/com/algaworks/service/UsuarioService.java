package com.algaworks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.model.Usuario;
import com.algaworks.repository.Usuarios;
import com.algaworks.service.exception.EmailUsuarioJaCadastradoException;

@Service
public class UsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> emailExistente = usuarios.findByEmail(usuario.getEmail());
		
		if(emailExistente.isPresent()) {
			throw new EmailUsuarioJaCadastradoException("e-Mail já cadastrado");
		}
		
		usuarios.save(usuario);
	}
}
