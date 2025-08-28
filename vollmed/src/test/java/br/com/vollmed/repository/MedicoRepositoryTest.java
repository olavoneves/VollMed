package br.com.vollmed.repository;

import br.com.vollmed.dto.CadastroMedicoDTO;
import br.com.vollmed.dto.CadastroPacienteDTO;
import br.com.vollmed.dto.EnderecoDTO;
import br.com.vollmed.model.Consulta;
import br.com.vollmed.model.Especialidade;
import br.com.vollmed.model.Medico;
import br.com.vollmed.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Se um único médico cadastrado já tiver consulta na data, deve retornar null")
    void escolherMedicoAleatorioLivreCenario01() {
        // Given ou Arrange
        var proximaSegunda = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        var dezHoras = LocalTime.of(10, 0);
        var medico = cadastrarMedico("Marcos", "marcos@gmail.com", "1236", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Flora", "florinha@gmail.com", "90127843321");
        cadastrarConsulta(paciente, medico, proximaSegunda, dezHoras);

        // When ou Act
        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivre(Especialidade.CARDIOLOGIA, proximaSegunda, dezHoras);

        // Then ou Assert
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Se um médico estiver disponivel na data, deve retornar esse médico")
    void escolherMedicoAleatorioLivreCenario02() {
        var proximaSegunda = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        var dezHoras = LocalTime.of(10, 0);

        var medico = cadastrarMedico("Marcos", "marcos@gmail.com", "12367", Especialidade.CARDIOLOGIA);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivre(Especialidade.CARDIOLOGIA, proximaSegunda, dezHoras);
        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void cadastrarConsulta(Paciente paciente, Medico medico, LocalDate data, LocalTime hora) {
        testEntityManager.persist(new Consulta(null, paciente, medico, data, hora));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        testEntityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        testEntityManager.persist(paciente);
        return paciente;
    }

    private CadastroMedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new CadastroMedicoDTO(
                nome,
                email,
                "2208923211",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private CadastroPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new CadastroPacienteDTO(
                nome,
                email,
                "2208923211",
                cpf,
                dadosEndereco()
        );
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "rua xpto",
                "bairro",
                "000000",
                "brasilia",
                "DF",
                "56C"
        );
    }
}