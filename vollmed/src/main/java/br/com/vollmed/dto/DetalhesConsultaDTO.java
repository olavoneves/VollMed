package br.com.vollmed.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record DetalhesConsultaDTO(Long id,
                                  Long idPaciente,
                                  Long idMedico,
                                  LocalDate data,
                                  LocalTime hora) {
}
