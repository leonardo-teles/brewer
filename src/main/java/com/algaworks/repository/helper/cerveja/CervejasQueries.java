package com.algaworks.repository.helper.cerveja;

import java.util.List;

import com.algaworks.model.Cerveja;
import com.algaworks.repository.filter.CervejaFilter;

public interface CervejasQueries {

	public List<Cerveja> filtrar(CervejaFilter filtro);
}
