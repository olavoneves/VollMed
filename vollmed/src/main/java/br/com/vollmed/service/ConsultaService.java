package br.com.vollmed.service;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.dto.DetalhesConsultaDTO;
import br.com.vollmed.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public ResponseEntity agendarConsulta(@RequestBody DetalhesAgendamentoConsultaDTO dados) {
        System.out.println(dados);
        return ResponseEntity.ok(new DetalhesConsultaDTO(null, null, null, null, null));
    }
}
