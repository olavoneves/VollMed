package br.com.vollmed.model;

import br.com.vollmed.dto.AtualizaMedicoDTO;
import br.com.vollmed.dto.CadastroMedicoDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Medico(CadastroMedicoDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarDados(AtualizaMedicoDTO dados) {
        if (!dados.nome().isEmpty()) {
            this.nome = dados.nome();
        }

        if (!dados.telefone().isEmpty()) {
            this.telefone = dados.telefone();
        }

        if (dados.endereco() != null) {
            this.endereco.atualizarEndereco(dados.endereco());
        }
    }

    public void excluirMedico() {
        this.ativo = false;
    }
}
