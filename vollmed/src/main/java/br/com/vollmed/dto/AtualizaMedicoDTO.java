package br.com.vollmed.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizaMedicoDTO(@NotNull Long id,
                                @NotBlank String nome,
                                String telefone,
                                EnderecoDTO endereco) {
}
