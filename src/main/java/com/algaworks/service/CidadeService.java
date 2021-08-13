package com.algaworks.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.model.Cidade;
import com.algaworks.repository.Cidades;
import com.algaworks.service.exception.NomeCidadeJaCadastradoException;

@Service
public class CidadeService {

	@Autowired
	private Cidades cidades;
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		
		if (cidadeExistente.isPresent()) {
			throw new NomeCidadeJaCadastradoException("Nome da cidade já cadastrada");
		}
		
		return cidades.saveAndFlush(cidade);
	}
}
