package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import br.com.vollmed.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaClinica implements IValidador{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DetalhesAgendamentoConsultaDTO dados) {
        var medicoPossuiOutraConsultaNoHorario = consultaRepository.existsByMedicoIdAndDataAndHora(dados.idMedico(), dados.data(), dados.hora());
        if (medicoPossuiOutraConsultaNoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta no mesmo horário");
        }
    }
}
