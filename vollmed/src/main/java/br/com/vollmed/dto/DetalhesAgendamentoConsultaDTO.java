package br.com.vollmed.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record DetalhesAgendamentoConsultaDTO(Long idPaciente,
                                             @NotNull Long idMedico,
                                             @NotNull LocalDate data,
                                             @NotNull LocalTime hora) {

}
