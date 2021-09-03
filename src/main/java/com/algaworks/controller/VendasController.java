package com.algaworks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.model.Cerveja;
import com.algaworks.repository.Cervejas;
import com.algaworks.session.TabelaItemVenda;

@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private TabelaItemVenda tabelaItemVenda;
	

	@GetMapping("/nova")
	public String nova() {
		return "venda/cadastro";
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja) {
		Cerveja cerveja = cervejas.findOne(codigoCerveja);
		tabelaItemVenda.adicionarItem(cerveja, 1);
		ModelAndView mv = new ModelAndView("venda/tabelaItemVenda");
		mv.addObject("itens", tabelaItemVenda.getItens());
		
		return mv;
	}
}
