package br.com.vollmed.repository;

import br.com.vollmed.model.Paciente;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("""
            SELECT paciente.ativo
            FROM Paciente paciente
            WHERE
            paciente.id = :id
            """)
    Boolean findAtivoById(Long idPaciente);
}
