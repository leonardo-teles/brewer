package com.algaworks.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.model.Cerveja;
import com.algaworks.repository.Cervejas;
import com.algaworks.session.TabelaItemSession;

@Controller
@RequestMapping("/vendas")
public class VendasController {
	
	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private TabelaItemSession tabelaItem;

	@GetMapping("/nova")
	public ModelAndView nova() {
		ModelAndView mv = new ModelAndView("venda/cadastro");
		mv.addObject("uuid", UUID.randomUUID().toString());
		
		return mv;
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCerveja, String uuid) {
		Cerveja cerveja = cervejas.findById(codigoCerveja).orElse(null);
		tabelaItem.adicionarItem(uuid, cerveja, 1);
		
		return mvTabelaItemVenda(uuid);
	}
	
	@PutMapping("/item/{codigoCerveja}")
	public ModelAndView alterarQuantidadeItem(@PathVariable("codigoCerveja") Cerveja cerveja, Integer quantidade, String uuid) {
		tabelaItem.alterarQuantidadeItens(uuid, cerveja, quantidade);

		return mvTabelaItemVenda(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoCerveja}") 
	public ModelAndView excluirItem(@PathVariable("codigoCerveja") Cerveja cerveja, @PathVariable String uuid) {
		tabelaItem.excluirItem(uuid, cerveja);
		
		return mvTabelaItemVenda(uuid);
	}

	private ModelAndView mvTabelaItemVenda(String uuid) {
		ModelAndView mv = new ModelAndView("venda/tabela");
		mv.addObject("itens", tabelaItem.getItens(uuid));
		mv.addObject("valorTotal", tabelaItem.getValorTotal(uuid));
		
		return mv;
	}
}
