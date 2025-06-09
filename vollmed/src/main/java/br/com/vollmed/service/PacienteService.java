package br.com.vollmed.service;

import br.com.vollmed.dto.AtualizaPacienteDTO;
import br.com.vollmed.dto.CadastroPacienteDTO;
import br.com.vollmed.dto.ListagemPacienteDTO;
import br.com.vollmed.model.Paciente;
import br.com.vollmed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public void cadastrarPaciente(CadastroPacienteDTO dados) {
        repository.save(new Paciente(dados));
    }

    public Page<ListagemPacienteDTO> listarPaciente(Pageable pagination) {
        return repository.findAll(pagination)
                .map(ListagemPacienteDTO::new);
    }

    public void atualizarDadosPaciente(AtualizaPacienteDTO dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);
    }

    public void excluirPaciente(Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluirPaciente();
    }
}
