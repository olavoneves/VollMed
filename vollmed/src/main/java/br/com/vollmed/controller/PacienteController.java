package br.com.vollmed.controller;

import br.com.vollmed.dto.PacienteDTO;
import br.com.vollmed.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public void cadastrarPaciente(@RequestBody @Valid PacienteDTO dados) {
        service.cadastrarPaciente(dados);
    }
}
