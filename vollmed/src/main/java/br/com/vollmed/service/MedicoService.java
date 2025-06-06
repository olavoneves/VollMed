package br.com.vollmed.service;

import br.com.vollmed.dto.CadastroMedicoDTO;
import br.com.vollmed.dto.ListagemMedicoDTO;
import br.com.vollmed.model.Medico;
import br.com.vollmed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository repository;

    public void cadastrarMedico(CadastroMedicoDTO dados) {
        repository.save(new Medico(dados));
    }

    public Page<ListagemMedicoDTO> listarMedico(Pageable pagination) {
        return repository.findAll(pagination)
                .map(ListagemMedicoDTO::new);
    }
}
