package com.algaworks.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.enums.Origem;
import com.algaworks.enums.Sabor;
import com.algaworks.model.Cerveja;
import com.algaworks.repository.Estilos;

@Controller
@RequestMapping("/cervejas")
public class CervejaController {
	
	@Autowired
	private Estilos estilos;
	
	@GetMapping("/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/cadastro");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes attributes) {
		/*
		 * if(result.hasErrors()) { return novo(cerveja); }
		 */
		
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		System.out.println(">>>>> sku: " + cerveja.getSku());
		System.out.println(">>>>> sabor: " + cerveja.getSabor());
		System.out.println(">>>>> origem: " + cerveja.getOrigem());
		
		System.out.println(">>>>> estilo: " + cerveja.getEstilo());
		
		return new ModelAndView("redirect:/cervejas/novo");
	}
}
