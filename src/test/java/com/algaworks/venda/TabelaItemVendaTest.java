package com.algaworks.venda;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algaworks.model.Cerveja;
import com.algaworks.session.TabelaItemVenda;

public class TabelaItemVendaTest {

	private TabelaItemVenda tabelaItemVenda;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.tabelaItemVenda = new TabelaItemVenda();
	}
	
	@Test
	public void deveCalcularValorTotalSemItens() throws Exception {
		Assertions.assertEquals(BigDecimal.ZERO, tabelaItemVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComUmItem() throws Exception {
		Cerveja cerveja = new Cerveja();
		BigDecimal valor = new BigDecimal("8.90");
		cerveja.setValor(valor);
		
		tabelaItemVenda.adicionarItem(cerveja, 1);
		
		Assertions.assertEquals(valor, tabelaItemVenda.getValorTotal());
	}
	
	@Test
	public void deveCalcularValorTotalComVariosItens() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		
		BigDecimal v1 = new BigDecimal("8.90");
		c1.setValor(v1);
		
		Cerveja c2 = new Cerveja();
		c2.setCodigo(2L);
		
		BigDecimal v2 = new BigDecimal("4.99");
		c2.setValor(v2);
		
		tabelaItemVenda.adicionarItem(c1, 1);
		tabelaItemVenda.adicionarItem(c2, 2);
		
		Assertions.assertEquals(new BigDecimal("18.88"), tabelaItemVenda.getValorTotal());
	}
	
	@Test
	public void deveManterTamanhoDaListaParaMesmasCervejas() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		tabelaItemVenda.adicionarItem(c1, 1);
		tabelaItemVenda.adicionarItem(c1, 1);
		
		assertEquals(1, tabelaItemVenda.total());
		assertEquals(new BigDecimal("9.00"), tabelaItemVenda.getValorTotal());
	}
	
	@Test
	public void deveAlterarQuantidadeDoItem() throws Exception {
		Cerveja c1 = new Cerveja();
		c1.setCodigo(1L);
		c1.setValor(new BigDecimal("4.50"));
		
		tabelaItemVenda.adicionarItem(c1, 1);
		tabelaItemVenda.alterarQuantidadeItens(c1, 3);
		
		assertEquals(1, tabelaItemVenda.total());
		assertEquals(new BigDecimal("13.50"), tabelaItemVenda.getValorTotal());
	}
}
