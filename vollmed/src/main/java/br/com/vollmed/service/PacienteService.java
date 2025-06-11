package br.com.vollmed.service;

import br.com.vollmed.dto.AtualizaPacienteDTO;
import br.com.vollmed.dto.CadastroPacienteDTO;
import br.com.vollmed.dto.DetalhesPacienteDTO;
import br.com.vollmed.dto.ListagemPacienteDTO;
import br.com.vollmed.model.Paciente;
import br.com.vollmed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public ResponseEntity cadastrarPaciente(CadastroPacienteDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = new Paciente(dados);
        repository.save(paciente);
        var uri = uriComponentsBuilder.path("/api/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPacienteDTO(paciente));
    }

    public ResponseEntity<Page<ListagemPacienteDTO>> listarPaciente(Pageable pagination) {
        var page = repository.findAll(pagination)
                .map(ListagemPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity atualizarDadosPaciente(AtualizaPacienteDTO dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarDados(dados);
        return ResponseEntity.ok(new DetalhesPacienteDTO(paciente));
    }

    public ResponseEntity excluirPaciente(Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluirPaciente();
        return ResponseEntity.noContent().build();
    }
}
