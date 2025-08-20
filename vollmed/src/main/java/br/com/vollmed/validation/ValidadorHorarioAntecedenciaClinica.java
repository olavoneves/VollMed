package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;

@Component
public class ValidadorHorarioAntecedenciaClinica implements IValidador{

    public void validar(DetalhesAgendamentoConsultaDTO dados) {
        var horaConsulta = dados.hora();
        var agora = LocalTime.now();
        var diferencaMinutos = Duration.between(agora, horaConsulta).toMinutes();

        if (diferencaMinutos > 30) {
            throw new ValidacaoException("Consulta precisa ser agendada com 30 minutos de antecedencia");
        }
    }

}
