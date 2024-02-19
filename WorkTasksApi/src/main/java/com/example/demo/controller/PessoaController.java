package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entitys.Pessoa;
import com.example.demo.entitys.Tarefa;
import com.example.demo.repository.PessoaRepository;

@RestController
public class PessoaController {

	private PessoaRepository pessoaRepository;

	public PessoaController(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@PostMapping("post/pessoas")
	public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	@PutMapping("put/pessoas/{id}")
	public Pessoa alterarPessoa(@PathVariable Long id, @RequestBody Pessoa novaPessoa) {
		return pessoaRepository.findById(id).map(pessoa -> {
			pessoa.setNome(novaPessoa.getNome());
			pessoa.setDepartamento(novaPessoa.getDepartamento());
			return pessoaRepository.save(pessoa);
		}).orElseGet(() -> {
			novaPessoa.setId(id);
			return pessoaRepository.save(novaPessoa);
		});
	}

	@DeleteMapping("delete/pessoas/{id}")
	public void removerPessoa(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}
	
	 @GetMapping("get/pessoas")
	    public List<?> getPessoas() {
	        return pessoaRepository.findAll().stream().map(pessoa -> {
	            String nome = pessoa.getNome();
	            String departamento = pessoa.getDepartamento().getIdDepartamento().toString();
	            int totalHoras = pessoa.getTarefas().stream().mapToInt(Tarefa::getDuracao).sum();
	            return new Object[]{nome, departamento, totalHoras};
	        }).collect(Collectors.toList());
	    }
	 
	 @GetMapping("get/pessoas/gastos")
	 public List<?> getPessoasGastos(@RequestParam String nome, 
	                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio, 
	                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	     return pessoaRepository.findByNome(nome).stream()
	         .filter(pessoa -> pessoa.getTarefas().stream()
	             .anyMatch(tarefa -> {
	                 LocalDate prazo = LocalDate.parse(tarefa.getPrazo(), formatter);
	                 return !prazo.isBefore(inicio) && !prazo.isAfter(fim);
	             }))
	         .map(pessoa -> {
	             double mediaHoras = pessoa.getTarefas().stream()
	                 .filter(tarefa -> {
	                     LocalDate prazo = LocalDate.parse(tarefa.getPrazo(), formatter);
	                     return !prazo.isBefore(inicio) && !prazo.isAfter(fim);
	                 })
	                 .mapToInt(Tarefa::getDuracao)
	                 .average()
	                 .orElse(0);
	             return new Object[]{pessoa.getNome(), mediaHoras};
	         })
	         .collect(Collectors.toList());
	 }

}