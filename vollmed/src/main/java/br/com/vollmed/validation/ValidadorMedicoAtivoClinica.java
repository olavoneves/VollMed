package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.infra.exception.ValidacaoException;
import br.com.vollmed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivoClinica implements IValidador{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DetalhesAgendamentoConsultaDTO dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var medicoIsAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (!medicoIsAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada sem médico");
        }
    }

}
