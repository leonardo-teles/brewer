package com.algaworks.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.model.Cerveja;

@Controller
@RequestMapping("/cervejas")
public class CervejaController {
	
	@GetMapping("/novo")
	public String novo(Cerveja cerveja) {
		return "cerveja/cadastro";
	}
	
	@PostMapping("/novo")
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(cerveja);
		}
		
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		
		return "redirect:/cervejas/novo";
	}
}
