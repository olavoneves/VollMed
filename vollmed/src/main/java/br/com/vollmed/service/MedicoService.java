package br.com.vollmed.service;

import br.com.vollmed.dto.*;
import br.com.vollmed.model.Medico;
import br.com.vollmed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public ResponseEntity cadastrarMedico(CadastroMedicoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);
        var uri = uriComponentsBuilder.path("/api/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMedicoDTO(medico));
    }

    public ResponseEntity<Page<ListagemMedicoDTO>> listarMedico(Pageable pagination) {
        var page = repository.findAllByAtivoTrue(pagination)
                .map(ListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }

    public ResponseEntity atualizarDadosMedico(AtualizaMedicoDTO dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarDados(dados);
        return ResponseEntity.ok(new DetalhesMedicoDTO(medico));
    }

    public ResponseEntity excluirMedico(Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluirMedico();
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity detalharMedico(Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesMedicoDTO(medico));
    }
}
