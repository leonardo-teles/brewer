package com.algaworks.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import com.algaworks.model.Usuario;
import com.algaworks.repository.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
	public List<Usuario> filtrar(UsuarioFilter filtro);
}
