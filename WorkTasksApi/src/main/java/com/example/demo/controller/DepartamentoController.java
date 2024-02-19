package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;
import com.example.demo.entitys.Departamento;
import com.example.demo.repository.DepartamentoRepository;
import com.example.demo.repository.TarefaRepository;

@RestController
@RequestMapping("")
public class DepartamentoController {

    
    private DepartamentoRepository departamentoRepository;
    
    private TarefaRepository tarefaRepository;
    
    public DepartamentoController(DepartamentoRepository departamentoRepository, TarefaRepository tarefaRepository) {
        this.departamentoRepository = departamentoRepository;
        this.tarefaRepository = tarefaRepository;
    }


    @PostMapping
    public Departamento adicionarDepartamento(@RequestBody Departamento departamento) {
        return departamentoRepository.save(departamento);
    }
    
    @GetMapping("get/departamentos")
    public List<Object> getDepartamentos() {
        return departamentoRepository.findAll().stream().map(departamento -> {
            String idDepartamento = departamento.getIdDepartamento().toString();
            long quantidadePessoas = departamento.getPessoas().size();
            long quantidadeTarefas = departamento.getPessoas().stream()
                .flatMap(pessoa -> pessoa.getTarefas().stream())
                .count();
            return new Object[]{idDepartamento, quantidadePessoas, quantidadeTarefas};
        }).collect(Collectors.toList());
    }
    
    @GetMapping("get/departamentos/{id}")
    public Map<String, Object> getDepartamento(@PathVariable Integer id) {
        Departamento departamento = departamentoRepository.findById(id).orElse(null);
        if (departamento != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("idDepartamento", departamento.getIdDepartamento().toString());
            response.put("quantidadePessoas", departamento.getPessoas().size());
            response.put("quantidadeTarefasAnexadasAPessoas", departamento.getPessoas().stream()
                .flatMap(pessoa -> pessoa.getTarefas().stream())
                .count());
            response.put("quantidadeTotalDeTarefas", tarefaRepository.count());
            return response;
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Departamento não encontrado");
            return response;
        }
    }
}
