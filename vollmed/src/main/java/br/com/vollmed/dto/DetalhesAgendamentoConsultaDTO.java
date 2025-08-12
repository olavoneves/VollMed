package br.com.vollmed.dto;

import br.com.vollmed.model.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DetalhesAgendamentoConsultaDTO(Long idPaciente,
                                             @NotNull Long idMedico,
                                             @NotNull LocalDate data,
                                             @NotNull LocalTime hora,
                                            Especialidade especialidade) {

}
