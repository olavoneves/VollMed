package br.com.vollmed.controller;

import br.com.vollmed.dto.AtualizaPacienteDTO;
import br.com.vollmed.dto.CadastroPacienteDTO;
import br.com.vollmed.dto.ListagemPacienteDTO;
import br.com.vollmed.service.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid CadastroPacienteDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        return service.cadastrarPaciente(dados, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDTO>> listarPaciente(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pagination) {
        return service.listarPaciente(pagination);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDadosPaciente(@RequestBody @Valid AtualizaPacienteDTO dados) {
        return service.atualizarDadosPaciente(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPaciente(@PathVariable Long id) {
        return service.excluirPaciente(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPaciente(@PathVariable Long id) {
        return service.detalharPaciente(id);
    }
}
