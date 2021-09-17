package com.algaworks.mail;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.algaworks.model.Cerveja;
import com.algaworks.model.ItemVenda;
import com.algaworks.model.Venda;
import com.algaworks.storage.FotoStorage;

@Component
public class Mailer {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Mailer.class);

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine thymeleaf;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Async
	@SuppressWarnings("unchecked")
	public void enviar(Venda venda) {
		Context context = new Context();
		context.setVariable("venda", venda);
		context.setVariable("logo", "logo");
		
		Map<String, String> fotos = new HashedMap();
		boolean adicionarMockCerveja = false;
		
		for(ItemVenda item : venda.getItens()) {
			Cerveja cerveja = item.getCerveja();
			
			if(cerveja.temfoto()) {
				String cid = "foto-" + cerveja.getCodigo();
				context.setVariable(cid, cid);
				
				fotos.put(cid, cerveja.getFoto() + "|" + cerveja.getContentType());
			} else {
				adicionarMockCerveja = true;
				context.setVariable("mockCerveja", "mockCerveja");
			}
		}
		
		try {
			String email = thymeleaf.process("mail/resumo", context);
		
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			helper.setFrom("teste@mail.com");
			helper.setTo(venda.getCliente().getEmail());
			helper.setSubject("Brewer - Venda Realizada");
			helper.setText(email, true);
			
			helper.addInline("logo", new ClassPathResource("static/images/logo-gray.png"));
			
			if(adicionarMockCerveja) {
				helper.addInline("mockCerveja", new ClassPathResource("static/images/cerveja-mock.png"));
			}
			
			for(String cid : fotos.keySet()) {
				String[] fotoContentType = fotos.get(cid).split("\\|");
				String foto = fotoContentType[0];
				String contentType = fotoContentType[1];
				byte[] arrayFoto = fotoStorage.recuperarThumbnail(foto);
				helper.addInline(cid, new ByteArrayResource(arrayFoto), contentType);
			}
			
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			LOGGER.error("Erro ao enviar e-Mail", e);
		}
	}
}
