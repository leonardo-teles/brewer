package com.algaworks.session;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.algaworks.model.Cerveja;

@Component
@SessionScope
public class TabelaItemSession {

	private Set<TabelaItemVenda> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Cerveja cerveja, int i) {
		// TODO Auto-generated method stub
		
	}

	public void alterarQuantidadeItens(String uuid, Cerveja cerveja, Integer quantidade) {
		// TODO Auto-generated method stub
		
	}

	public void excluirItem(Cerveja cerveja) {
		// TODO Auto-generated method stub
		
	}

	public Object getItens(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
