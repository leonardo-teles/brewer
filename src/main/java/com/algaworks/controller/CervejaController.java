package com.algaworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.algaworks.model.Cerveja;

@Controller
@RequestMapping("/cervejas")
public class CervejaController {
	
	@GetMapping("/novo")
	public String novo() {
		return "cerveja/cadastro";
	}
	
	@PostMapping("/novo")
	public String cadastrar(Cerveja cerveja) {
		System.out.println(">>>>> sku " + cerveja.getSku());
		
		return "cerveja/cadastro";
	}
}
