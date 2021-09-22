package com.algaworks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.model.Cerveja;
import com.algaworks.repository.Cervejas;
import com.algaworks.service.event.cerveja.CervejaEvent;
import com.algaworks.service.exception.ImpossivelExcluirEntidadeException;
import com.algaworks.storage.FotoStorage;

@Service
public class CervejaService {

	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Cerveja cerveja) {
		cervejas.save(cerveja);
		
		publisher.publishEvent(new CervejaEvent(cerveja));
	}
	
	@Transactional
	public void excluir(Cerveja cerveja) {
		try {
			String foto = cerveja.getFoto();
			
			cervejas.delete(cerveja);
			cervejas.flush();
			
			fotoStorage.excluir(foto);
		} catch (DataIntegrityViolationException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluír cerveja. Já foi usada em alguma venda.");
		}
	}
}
