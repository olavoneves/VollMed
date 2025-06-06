package br.com.vollmed.service;

import br.com.vollmed.dto.MedicoDTO;
import br.com.vollmed.model.Medico;
import br.com.vollmed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public void cadastrarMedico(MedicoDTO dados) {
        repository.save(new Medico(dados));
    }
}
