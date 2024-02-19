package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitys.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	List<Pessoa> findByNome(String nome);
}
