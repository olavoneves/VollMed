package br.com.vollmed.dto;

import br.com.vollmed.model.Endereco;
import br.com.vollmed.model.Paciente;

public record DetalhesPacienteDTO(Long id,
                                  String nome,
                                  String email,
                                  Endereco endereco) {
    public DetalhesPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getEndereco());
    }
}
