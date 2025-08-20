package br.com.vollmed.validation;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;

public interface IValidador {
    void validar(DetalhesAgendamentoConsultaDTO dados);
}
