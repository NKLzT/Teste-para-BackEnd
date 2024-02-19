package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entitys.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

}
