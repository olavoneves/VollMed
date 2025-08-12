package br.com.vollmed.dto;

import br.com.vollmed.model.Consulta;
import jakarta.validation.constraints.NotNull;

public record DetalhesCancelamentoConsultaDTO(@NotNull Consulta consulta,
                                              @NotNull String motivoCancelamento) {
}
