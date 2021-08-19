package com.algaworks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.model.Usuario;
import com.algaworks.repository.Usuarios;
import com.algaworks.service.exception.EmailUsuarioJaCadastradoException;
import com.algaworks.service.exception.SenhaObrigatoriaUsuarioException;

@Service
public class UsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> emailExistente = usuarios.findByEmail(usuario.getEmail());
		
		if(emailExistente.isPresent()) {
			throw new EmailUsuarioJaCadastradoException("e-Mail já cadastrado");
		}
		
		if(usuario.isNovo() && !StringUtils.hasText(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha obrigatória para novos usuários");
		}
		
		if(usuario.isNovo()) {
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		usuarios.save(usuario);
	}
}
