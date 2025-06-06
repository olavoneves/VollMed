package br.com.vollmed.service;

import br.com.vollmed.dto.PacienteDTO;
import br.com.vollmed.model.Paciente;
import br.com.vollmed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public void cadastrarPaciente(PacienteDTO dados) {
        repository.save(new Paciente(dados));
    }
}
