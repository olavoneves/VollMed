package br.com.vollmed.controller;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.dto.DetalhesCancelamentoConsultaDTO;
import br.com.vollmed.dto.DetalhesConsultaDTO;
import br.com.vollmed.service.ConsultaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DetalhesAgendamentoConsultaDTO dados) {
        return consultaService.agendarConsulta(dados);
    }

    @GetMapping
    public void cancelarConsulta(@RequestBody @Valid DetalhesCancelamentoConsultaDTO dados) {
        consultaService.cancelarConsulta(dados);
    }
}
