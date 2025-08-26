package br.com.vollmed.dto;

import br.com.vollmed.model.Consulta;

import java.time.LocalDate;
import java.time.LocalTime;

public record DetalhesConsultaDTO(Long id,
                                  Long idPaciente,
                                  Long idMedico,
                                  LocalDate data,
                                  LocalTime hora) {
    public DetalhesConsultaDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getData(), consulta.getHora());
    }
}
