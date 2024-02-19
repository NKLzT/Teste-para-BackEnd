package DepartamentoControllerTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import com.example.demo.controller.DepartamentoController;
import com.example.demo.entitys.Departamento;
import com.example.demo.entitys.Pessoa;
import com.example.demo.entitys.Tarefa;
import com.example.demo.repository.DepartamentoRepository;

@ExtendWith(MockitoExtension.class)
public class DepartamentoControllerTest {

    @InjectMocks
    DepartamentoController departamentoController;

    @Mock
    DepartamentoRepository departamentoRepository;

    @Test
    public void testGetDepartamentos() {
        Tarefa tarefa1 = new Tarefa();
        tarefa1.setDuracao(2);

        Tarefa tarefa2 = new Tarefa();
        tarefa2.setDuracao(3);

        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setTarefas(tarefas);

        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(1);
        departamento.setPessoas(Arrays.asList(pessoa));

        when(departamentoRepository.findAll()).thenReturn(Arrays.asList(departamento));

        List<?> result = departamentoController.getDepartamentos();

        verify(departamentoRepository, times(1)).findAll();

        assert(result.size() == 1);
        assert(((Object[]) result.get(0))[0].equals("1"));
        assert(((Object[]) result.get(0))[1].equals(1L));
        assert(((Object[]) result.get(0))[2].equals(2L));
    }
}