package com.algaworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.model.Venda;

@Repository
public interface Vendas extends JpaRepository<Venda, Long> {

}
