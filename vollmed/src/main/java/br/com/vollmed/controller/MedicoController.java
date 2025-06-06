package br.com.vollmed.controller;

import br.com.vollmed.dto.CadastroMedicoDTO;
import br.com.vollmed.dto.ListagemMedicoDTO;
import br.com.vollmed.service.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public List<ListagemMedicoDTO> listarMedico() {
        return service.listarMedico();
    }
}
