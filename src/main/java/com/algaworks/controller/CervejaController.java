package com.algaworks.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String novo() {
		return "cerveja/cadastro";
	}
	
	@PostMapping("/novo")
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			model.addAttribute("mensagem", "Erro no formulário");
			
			return "cerveja/cadastro";
		}
		
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		
		return "redirect:/cervejas/novo";
	}
}
