package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements IValidador{

    public void validar(DetalhesAgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var horaConsulta = dados.hora();

        // Métodos booleanos
        var sunday = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpenClinica = horaConsulta.getHour() < 7;
        var afterFinishClinica = horaConsulta.getHour() > 18;

        if (sunday || beforeOpenClinica || afterFinishClinica) {
            throw new ValidacaoException("Consulta fora de Data ou Horário de funcionamento da Clínica");
        }
    }

}
