package com.example.demo.PessoaControllerTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.demo.entitys.Pessoa;
import com.example.demo.entitys.Tarefa;
import com.example.demo.controller.PessoaController;
import com.example.demo.entitys.Departamento;
import com.example.demo.repository.PessoaRepository;
import com.example.demo.repository.DepartamentoRepository;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

	@InjectMocks
	PessoaController pessoaController;

	@Mock
	PessoaRepository pessoaRepository;

	@Mock
	DepartamentoRepository departamentoRepository;

	@Test
	public void testAdicionarPessoa() {
		Departamento departamento = new Departamento();
		departamento.setIdDepartamento(1);

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("João");
		pessoa.setDepartamento(departamento);

		pessoaController.adicionarPessoa(pessoa);

		verify(pessoaRepository, times(1)).save(pessoa);
	}

	@Test
	public void testAlterarPessoa() {
		Long id = 1L;
		Pessoa pessoaExistente = new Pessoa();
		pessoaExistente.setNome("João");

		Pessoa novaPessoa = new Pessoa();
		novaPessoa.setNome("Maria");

		when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaExistente));

		pessoaController.alterarPessoa(id, novaPessoa);

		verify(pessoaRepository, times(1)).findById(id);
		verify(pessoaRepository, times(1)).save(pessoaExistente);

		assert (pessoaExistente.getNome().equals(novaPessoa.getNome()));
	}

	@Test
	public void testRemoverPessoa() {
		Long id = 1L;

		pessoaController.removerPessoa(id);

		verify(pessoaRepository, times(1)).deleteById(id);
	}
	@Test
	public void testGetPessoas() {
	    Tarefa tarefa1 = new Tarefa();
	    tarefa1.setDuracao(2);

	    Tarefa tarefa2 = new Tarefa();
	    tarefa2.setDuracao(3);

	    List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2);

	    Departamento departamento = new Departamento();
	    departamento.setIdDepartamento(1);

	    Pessoa pessoa = new Pessoa();
	    pessoa.setNome("João");
	    pessoa.setTarefas(tarefas);
	    pessoa.setDepartamento(departamento);

	    when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoa));

	    List<?> result = pessoaController.getPessoas();

	    verify(pessoaRepository, times(1)).findAll();

	    assert(result.size() == 1);
	    assert(((Object[]) result.get(0))[0].equals("João"));
	    assert(((Object[]) result.get(0))[1].equals("1"));
	    assert(((Object[]) result.get(0))[2].equals(5));
	}
	
    @Test
    public void testGetPessoasGastos() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setDuracao(2);
        tarefa1.setPrazo("2024-12-31");

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDuracao(3);
        tarefa2.setPrazo("2024-12-31");

        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setTarefas(tarefas);

        when(pessoaRepository.findByNome("João")).thenReturn(Arrays.asList(pessoa));

        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 12, 31);

        List<?> result = pessoaController.getPessoasGastos("João", inicio, fim);

        verify(pessoaRepository, times(1)).findByNome("João");

        assert(result.size() == 1);
        assert(((Object[]) result.get(0))[0].equals("João"));
        assert(((Object[]) result.get(0))[1].equals(2.5));
    }
}
