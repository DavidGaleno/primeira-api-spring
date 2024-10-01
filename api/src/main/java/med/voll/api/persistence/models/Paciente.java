package med.voll.api.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.persistence.dto.paciente.DadosAtualizacoPacienteDTO;
import med.voll.api.persistence.dto.paciente.DadosCadastroPacienteDTO;

@Table(name = "pacientes")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private Boolean ativo;

    public Paciente(DadosCadastroPacienteDTO requisicao) {
        nome = requisicao.nome();
        email = requisicao.email();
        telefone = requisicao.telefone();
        cpf = requisicao.cpf();
        endereco = new Endereco(requisicao.endereco());
        ativo = true;
    }

    public Paciente(DadosAtualizacoPacienteDTO requisicao) {
        nome = requisicao.nome();
        telefone = requisicao.telefone();
        endereco = new Endereco(requisicao.endereco());
    }

    public void atualizar(DadosAtualizacoPacienteDTO dados) {
        nome = dados.nome();
        telefone = dados.telefone();
        endereco = new Endereco(dados.endereco());
    }

    public void deletar() {
        ativo = false;
    }
}
