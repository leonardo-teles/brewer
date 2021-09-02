package com.algaworks.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.model.Cerveja;
import com.algaworks.model.ItemVenda;

public class TabelaItemVenda {

	private List<ItemVenda> itens = new ArrayList<>();
	
	public BigDecimal getValorTotal() {
		return itens.stream().map(ItemVenda::getValorTotal).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
	}
	
	public void adicionarItem(Cerveja cerveja, Integer quantidade) {
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setCerveja(cerveja);
		itemVenda.setQuantidade(quantidade);
		itemVenda.setValorUnitario(cerveja.getValor());
		
		itens.add(itemVenda);
	}
}
