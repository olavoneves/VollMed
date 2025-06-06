package br.com.vollmed.dto;

public record PacienteDTO(String nome,
                          String email,
                          String telefone,
                          String cpf,
                          EnderecoDTO endereco) {
}
