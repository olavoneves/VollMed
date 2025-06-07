package br.com.vollmed.dto;

import br.com.vollmed.model.Especialidade;
import br.com.vollmed.model.Medico;

public record ListagemMedicoDTO(Long id,
                                String nome,
                                String email,
                                String crm,
                                Especialidade especialidade) {
    public ListagemMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
