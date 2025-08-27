package br.com.vollmed.controller;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.dto.DetalhesCancelamentoConsultaDTO;
import br.com.vollmed.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DetalhesAgendamentoConsultaDTO dados) {
        var dto = consultaService.agendarConsulta(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DetalhesCancelamentoConsultaDTO dados) {
        return consultaService.cancelarConsulta(dados);
    }
}
