package br.com.vollmed.dto;

import br.com.vollmed.model.Especialidade;

public record MedicoDTO(String nome,
                        String email,
                        String crm,
                        Especialidade especialidades,
                        EnderecoDTO endereco) {
}
