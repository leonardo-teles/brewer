package com.algaworks.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println(">>>>> tem erro sim!!");
		}
		
		System.out.println(">>>>> sku " + cerveja.getSku());
		
		return "cerveja/cadastro";
	}
}
