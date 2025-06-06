package br.com.vollmed.controller;

import br.com.vollmed.dto.CadastroMedicoDTO;
import br.com.vollmed.dto.ListagemMedicoDTO;
import br.com.vollmed.service.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid CadastroMedicoDTO dados) {
        service.cadastrarMedico(dados);
    }

    @GetMapping
    public Page<ListagemMedicoDTO> listarMedico(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pagination) {
        return service.listarMedico(pagination);
    }
}
