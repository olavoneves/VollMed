package br.com.vollmed.repository;

import br.com.vollmed.model.Especialidade;
import br.com.vollmed.model.Medico;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pagination);

    @Query("""
            SELECT medico FROM Medico medico
            WHERE
            medico.ativo = true
            AND
            medico.especialidade = :especialidade
            AND
            medico.id not in(
                SELECT consulta.medico.id FROM Consulta consulta
                WHERE
                consulta.data = :data
                AND
                consulta.hora = :hora
            )
            ORDER BY rand()
            LIMIT 1
            """)
    Medico escolherMedicoAleatorioLivre(Especialidade especialidade, @NotNull LocalDate data, @NotNull LocalTime hora);

    @Query("""
            SELECT medico.ativo
            FROM Medico medico
            WHERE
            medico.id = :id
            """)
    Boolean findAtivoById(Long id);
}
