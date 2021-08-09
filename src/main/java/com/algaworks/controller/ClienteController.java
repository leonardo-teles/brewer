package com.algaworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.enums.TipoPessoa;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("cliente/cadastro");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		return mv;
	}
}
