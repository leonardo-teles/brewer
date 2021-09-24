package com.algaworks.service.event.cerveja;

import org.springframework.util.ObjectUtils;

import com.algaworks.model.Cerveja;

public class CervejaEvent {

	private Cerveja cerveja;

	public CervejaEvent(Cerveja cerveja) {
		this.cerveja = cerveja;
	}

	public Cerveja getCerveja() {
		return cerveja;
	}
	
	public boolean temFoto() {
		return !ObjectUtils.isEmpty(cerveja.getFoto());
	}
	
	public boolean isNovaFoto() {
		return cerveja.isNovaFoto();
	}
}
