package br.com.vollmed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(@NotBlank @Pattern(regexp = "\\d{8}") String cep,
                          @NotBlank String logradouro,
                          @NotBlank String bairro,
                          @NotBlank String localidade,
                          String uf,
                          String complemento) {
}
