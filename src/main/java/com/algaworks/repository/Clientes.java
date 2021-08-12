package com.algaworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.model.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long> {

}
