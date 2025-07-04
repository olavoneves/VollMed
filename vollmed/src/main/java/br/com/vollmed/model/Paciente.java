package br.com.vollmed.model;

import br.com.vollmed.dto.AtualizaPacienteDTO;
import br.com.vollmed.dto.CadastroPacienteDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Paciente(CadastroPacienteDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarDados(AtualizaPacienteDTO dados) {
        if (!dados.nome().isEmpty()) {
            this.nome = dados.nome();
        }

        if (dados.endereco() != null) {
            this.endereco.atualizarEndereco(dados.endereco());
        }
    }

    public void excluirPaciente() {
        this.ativo = false;
    }
}
