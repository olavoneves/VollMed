package br.com.vollmed.controller;

import br.com.vollmed.dto.AtualizaPacienteDTO;
import br.com.vollmed.dto.CadastroPacienteDTO;
import br.com.vollmed.dto.ListagemPacienteDTO;
import br.com.vollmed.service.PacienteService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid CadastroPacienteDTO dados) {
        service.cadastrarPaciente(dados);
    }

    @GetMapping
    public Page<ListagemPacienteDTO> listarPaciente(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pagination) {
        return service.listarPaciente(pagination);
    }

    @PutMapping
    @Transactional
    public void atualizarDadosPaciente(@RequestBody @Valid AtualizaPacienteDTO dados) {
        service.atualizarDadosPaciente(dados);
    }
}
