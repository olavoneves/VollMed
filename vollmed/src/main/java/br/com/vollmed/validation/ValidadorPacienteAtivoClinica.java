package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import br.com.vollmed.repository.PacienteRepository;

public class ValidadorPacienteAtivoClinica {

    private PacienteRepository pacienteRepository;

    public void validar(DetalhesAgendamentoConsultaDTO dados) {
        if (dados.idPaciente() == null) {
            return;
        }

        var pacienteIsAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!pacienteIsAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada sem paciente");
        }
    }
}
