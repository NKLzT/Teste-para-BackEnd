package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.example.demo.entitys.Pessoa;
import com.example.demo.entitys.Tarefa;
import com.example.demo.repository.TarefaRepository;

@RestController
public class TarefaController {

	private TarefaRepository tarefaRepository;

	public TarefaController(TarefaRepository tarefaRepository) {
		this.tarefaRepository = tarefaRepository;
	}

	@PostMapping("/tarefas")
	public Tarefa addTarefa(@RequestBody Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
	}

	@PutMapping("/tarefas/finalizar/{id}")
	public Tarefa finalizarTarefa(@PathVariable Long id) {
		Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
		tarefa.setFinalizado(true);
		return tarefaRepository.save(tarefa);
	}

    @PutMapping("put/tarefas/alocar/{id}")
    public Tarefa alocarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        if (pessoa.getDepartamento() != null && tarefa.getidDepartamento() != null && 
            pessoa.getDepartamento().getIdDepartamento().equals(tarefa.getidDepartamento().getIdDepartamento())) {
            tarefa.setidPessoa(pessoa);
            return tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("A pessoa e a tarefa não estão no mesmo departamento ou a pessoa não tem um departamento associado");
        }
    }
    
    @GetMapping("get/tarefas/pendentes")
    public List<Tarefa> getTarefasPendentes() {
        return tarefaRepository.findAllByidPessoaIsNull(PageRequest.of(0, 3, Sort.by("prazo")));
    }
}
