package br.com.vollmed.service;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.dto.DetalhesCancelamentoConsultaDTO;
import br.com.vollmed.dto.DetalhesConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import br.com.vollmed.model.Consulta;
import br.com.vollmed.model.Medico;
import br.com.vollmed.repository.ConsultaRepository;
import br.com.vollmed.repository.MedicoRepository;
import br.com.vollmed.repository.PacienteRepository;
import br.com.vollmed.validation.IValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private List<IValidador> validadores;

    public DetalhesConsultaDTO agendarConsulta(@RequestBody DetalhesAgendamentoConsultaDTO dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe.");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do medico informado não existe.");
        }

        validadores.forEach(validador -> validador.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, paciente, medico, dados.data(), dados.hora());
        consultaRepository.save(consulta);
        return new DetalhesConsultaDTO(consulta);
    }

    public Medico escolherMedico(DetalhesAgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é o requisito mínimo obrigatório caso o médico não seja definido.");
        }

        return medicoRepository.escolherMedicoAleatorioLivre(dados.especialidade(), dados.data(), dados.hora());
    }

    public void cancelarConsulta(@RequestBody DetalhesCancelamentoConsultaDTO dados) {
        if (!consultaRepository.existsById(dados.consulta().getId())) {
            throw new ValidacaoException("Consulta é um requisito essencial para realização desse método.");
        }

        if (dados.motivoCancelamento().isEmpty()) {
            throw new ValidacaoException("Motivo do Cancelamento é um requisito obrigatório.");
        }

        consultaRepository.deleteById(dados.consulta().getId());
    }
}
