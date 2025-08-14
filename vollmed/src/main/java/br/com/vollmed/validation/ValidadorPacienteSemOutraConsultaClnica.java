package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import br.com.vollmed.repository.ConsultaRepository;

public class ValidadorPacienteSemOutraConsultaClnica {

    private ConsultaRepository consultaRepository;

    public void validar(DetalhesAgendamentoConsultaDTO dados) {
        var firstHour = dados.hora().withHour(7);
        var lastHour = dados.hora().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataAndHoraBetween(dados.idPaciente(), firstHour, lastHour);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui outra consulta no dia");
        }
    }

}
