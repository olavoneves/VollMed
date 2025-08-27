package br.com.vollmed.controller;

import br.com.vollmed.dto.AtualizaMedicoDTO;
import br.com.vollmed.dto.CadastroMedicoDTO;
import br.com.vollmed.dto.ListagemMedicoDTO;
import br.com.vollmed.service.MedicoService;
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
@RequestMapping("/api/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid CadastroMedicoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        return service.cadastrarMedico(dados, uriComponentsBuilder);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoDTO>> listarMedico(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pagination) {
        return service.listarMedico(pagination);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarDadosMedico(@RequestBody @Valid AtualizaMedicoDTO dados) {
        return service.atualizarDadosMedico(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirMedico(@PathVariable Long id) {
        return service.excluirMedico(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id) {
        return service.detalharMedico(id);
    }
}
