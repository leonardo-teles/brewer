package com.algaworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.model.Grupo;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

}
