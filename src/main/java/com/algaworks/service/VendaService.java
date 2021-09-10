package com.algaworks.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.model.Venda;
import com.algaworks.repository.Vendas;

@Service
public class VendaService {

	@Autowired
	private Vendas vendas;
	
	@Transactional
	public void salvar(Venda venda) {
		if(venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		}
		
		if(venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega(), venda.getHoraEntrega() != null ? venda.getHoraEntrega() : LocalTime.NOON));
		}
		
		vendas.save(venda);
	}
}
