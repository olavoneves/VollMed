package br.com.vollmed.repository;

import br.com.vollmed.model.Consulta;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByMedicoIdAndDataAndHora(Long idMedico, LocalDate data, LocalTime hora);

    Boolean existsByPacienteIdAndDataAndHoraBetween(Long idPaciente, LocalDate data, LocalTime firstHour, LocalTime lastHour);
}
