package com.algaworks.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.algaworks.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.algaworks.thymeleaf.processor.MessageElementTagProcessor;

@Component
public class BrewerDialect extends AbstractProcessorDialect {

	protected BrewerDialect() {
		super("Algaworks Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		
		return processadores;
	}
}
