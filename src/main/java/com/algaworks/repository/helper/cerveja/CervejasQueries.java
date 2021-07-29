package com.algaworks.repository.helper.cerveja;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.algaworks.model.Cerveja;
import com.algaworks.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public List<Cerveja> filtrar(CervejaFilter filtro, Pageable pageable);
}
