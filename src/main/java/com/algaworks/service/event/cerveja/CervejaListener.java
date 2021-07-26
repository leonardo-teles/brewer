package com.algaworks.service.event.cerveja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.storage.FotoStorage;

@Component
public class CervejaListener {
	
	@Autowired
	private FotoStorage fotoStorage;

	@EventListener(condition = "#evento.temFoto()")
	public void cervejaSalva(CervejaEvent evento) {
		fotoStorage.salvar(evento.getCerveja().getFoto());
	}
}
