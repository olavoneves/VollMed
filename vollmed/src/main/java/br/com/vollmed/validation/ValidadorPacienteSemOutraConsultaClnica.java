package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import br.com.vollmed.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaClnica implements IValidador{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DetalhesAgendamentoConsultaDTO dados) {
        var firstHour = dados.hora().withHour(7);
        var lastHour = dados.hora().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataAndHoraBetween(dados.idPaciente(), dados.data(), firstHour, lastHour);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui outra consulta no dia");
        }
    }

}
