package com.algaworks.mail;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

	@Async
	public void enviar() {
	}
}
