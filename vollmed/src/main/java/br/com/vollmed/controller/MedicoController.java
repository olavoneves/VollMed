package br.com.vollmed.controller;

import br.com.vollmed.dto.MedicoDTO;
import br.com.vollmed.repository.MedicoRepository;
import br.com.vollmed.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public void cadastrarMedico(@RequestBody MedicoDTO dados) {
        service.cadastrarMedico(dados);
    }
}
