package com.algaworks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.model.Cerveja;
import com.algaworks.repository.Cervejas;

@Service
public class CervejaService {

	@Autowired
	private Cervejas cervejas;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejas.save(cerveja);
	}
}
