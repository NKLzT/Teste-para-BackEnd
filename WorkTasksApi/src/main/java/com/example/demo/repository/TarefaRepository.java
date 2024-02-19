package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitys.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
	List<Tarefa> findAllByidPessoaIsNull(Pageable pageable);
}
