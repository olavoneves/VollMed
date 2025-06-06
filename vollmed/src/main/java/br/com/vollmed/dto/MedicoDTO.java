package br.com.vollmed.dto;

import br.com.vollmed.model.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record MedicoDTO(@NotBlank String nome,
                        @NotBlank @Email String email,
                        @NotBlank String telefone,
                        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
                        @NotNull Especialidade especialidade,
                        @NotNull @Valid EnderecoDTO endereco) {
}
