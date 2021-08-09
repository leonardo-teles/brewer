package com.algaworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.model.Estado;

@Repository
public interface Estados extends JpaRepository<Estado, Long> {

}
