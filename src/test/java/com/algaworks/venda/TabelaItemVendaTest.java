package com.algaworks.venda;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class TabelaItemVendaTest {

	private TabelaItemVenda tabelaItemVenda;
	
	@Before
	public void setUp() {
		this.tabelaItemVenda = new TabelaItemVenda();
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaItemVenda.getValorTotal());
	}
}
