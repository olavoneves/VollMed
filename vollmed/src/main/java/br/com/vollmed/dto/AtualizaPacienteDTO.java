package br.com.vollmed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizaPacienteDTO(@NotNull Long id,
                                  @NotBlank String nome,
                                  EnderecoDTO endereco) {
}
