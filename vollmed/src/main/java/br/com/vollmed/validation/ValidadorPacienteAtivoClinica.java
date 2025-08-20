package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import br.com.vollmed.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivoClinica implements IValidador{

    @Autowired
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
