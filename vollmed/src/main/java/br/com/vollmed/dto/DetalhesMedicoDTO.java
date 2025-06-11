package br.com.vollmed.dto;

import br.com.vollmed.model.Endereco;
import br.com.vollmed.model.Especialidade;
import br.com.vollmed.model.Medico;

public record DetalhesMedicoDTO(Long id,
                                String nome,
                                String email,
                                String crm,
                                Especialidade especialidade,
                                Endereco endereco) {
    public DetalhesMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
