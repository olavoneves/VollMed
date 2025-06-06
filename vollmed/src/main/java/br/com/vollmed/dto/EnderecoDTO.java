package br.com.vollmed.dto;

public record EnderecoDTO(String cep,
                          String logradouro,
                          String bairro,
                          String localidade,
                          String uf,
                          String complemento) {
}
