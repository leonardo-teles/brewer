package com.algaworks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@GetMapping("/novo")
	public String novo() {
		return "usuario/cadastro";
	}
}
