package TarefaControllerTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.data.domain.Sort;
import com.example.demo.controller.TarefaController;
import com.example.demo.entitys.Departamento;
import com.example.demo.entitys.Pessoa;
import com.example.demo.entitys.Tarefa;
import com.example.demo.repository.TarefaRepository;

@ExtendWith(MockitoExtension.class)
public class TarefaControllerTest {

    @InjectMocks
    TarefaController tarefaController;

    @Mock
    TarefaRepository tarefaRepository;

    @Test
    public void testAddTarefa() {
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(1);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setDepartamento(departamento);

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa Teste");
        tarefa.setDescricao("Descrição da Tarefa");
        tarefa.setPrazo("2024-12-31");
        tarefa.setDuracao(120);
        tarefa.setFinalizado(false);
        tarefa.setidDepartamento(departamento);
        tarefa.setidPessoa(pessoa);

        tarefaController.addTarefa(tarefa);

        verify(tarefaRepository, times(1)).save(tarefa);
    }
    
    @Test
    public void testAlocarPessoa() {
        Long id = 1L;

        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(1);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setDepartamento(departamento);

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa Teste");
        tarefa.setidDepartamento(departamento);

        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));

        tarefaController.alocarPessoa(id, pessoa);

        verify(tarefaRepository, times(1)).findById(id);
        verify(tarefaRepository, times(1)).save(tarefa);

        assert(tarefa.getidPessoa().equals(pessoa));
    }
    
    @Test
    public void testFinalizarTarefa() {
        Long id = 1L;

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa Teste");
        tarefa.setFinalizado(false);

        when(tarefaRepository.findById(id)).thenReturn(Optional.of(tarefa));

        tarefaController.finalizarTarefa(id);

        verify(tarefaRepository, times(1)).findById(id);
        verify(tarefaRepository, times(1)).save(tarefa);

        assert(tarefa.getFinalizado());
    }
    @Test
    public void testGetTarefasPendentes() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setTitulo("Tarefa Teste 1");
        tarefa1.setPrazo("2024-12-31");

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setTitulo("Tarefa Teste 2");
        tarefa2.setPrazo("2024-12-30");

        Tarefa tarefa3 = new Tarefa();
        tarefa3.setTitulo("Tarefa Teste 3");
        tarefa3.setPrazo("2024-12-29");

        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2, tarefa3);

        when(tarefaRepository.findAllByidPessoaIsNull(PageRequest.of(0, 3, Sort.by("prazo")))).thenReturn(tarefas);

        List<Tarefa> result = tarefaController.getTarefasPendentes();

        verify(tarefaRepository, times(1)).findAllByidPessoaIsNull(PageRequest.of(0, 3, Sort.by("prazo")));

        assert(result.size() == 3);
        assert(result.get(0).getTitulo().equals("Tarefa Teste 1"));
        assert(result.get(1).getTitulo().equals("Tarefa Teste 2"));
        assert(result.get(2).getTitulo().equals("Tarefa Teste 3"));
    }
}
