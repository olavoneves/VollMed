package br.com.vollmed.model;

import br.com.vollmed.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String complemento;

    public Endereco(EnderecoDTO endereco) {
        this.cep = endereco.cep();
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.localidade = endereco.localidade();
        this.uf = endereco.uf();
        this.complemento = endereco.complemento();
    }

    public void atualizarEndereco(EnderecoDTO endereco) {
        if (endereco.cep().isEmpty()) {
            this.cep = endereco.cep();
        }

        if (endereco.logradouro().isEmpty()) {
            this.logradouro = endereco.logradouro();
        }

        if (endereco.bairro().isEmpty()) {
            this.bairro = endereco.bairro();
        }

        if (endereco.localidade().isEmpty()) {
            this.localidade = endereco.localidade();
        }

        if (endereco.uf().isEmpty()) {
            this.uf = endereco.uf();
        }

        if (endereco.complemento().isEmpty()) {
            this.complemento = endereco.complemento();
        }
    }
}
