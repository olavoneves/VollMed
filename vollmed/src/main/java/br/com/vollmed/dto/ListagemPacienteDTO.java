package br.com.vollmed.dto;

import br.com.vollmed.model.Paciente;

public record ListagemPacienteDTO(String nome,
                                  String email,
                                  String telefone) {

    public ListagemPacienteDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }

}
