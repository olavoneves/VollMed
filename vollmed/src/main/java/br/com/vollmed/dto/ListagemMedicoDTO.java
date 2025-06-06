package br.com.vollmed.dto;

import br.com.vollmed.model.Especialidade;
import br.com.vollmed.model.Medico;

public record ListagemMedicoDTO(String nome,
                                String email,
                                String crm,
                                Especialidade especialidade) {
    public ListagemMedicoDTO(Medico medico) {
        this();
    }
}
