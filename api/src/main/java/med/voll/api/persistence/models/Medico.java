package med.voll.api.persistence.models;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.persistence.dto.medico.DadosAtualizacaoMedicoDTO;
import med.voll.api.persistence.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.persistence.enums.Especialidade;

@Table(name = "medicos")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedicoDTO dadosCadastroMedicoDTO) {
        nome = dadosCadastroMedicoDTO.nome();
        email = dadosCadastroMedicoDTO.email();
        telefone = dadosCadastroMedicoDTO.telefone();
        especialidade = dadosCadastroMedicoDTO.especialidade();
        crm = dadosCadastroMedicoDTO.crm();
        endereco = new Endereco(dadosCadastroMedicoDTO.endereco());
        ativo = true;
    }

    public void atualizar(DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO) {
        if (dadosAtualizacaoMedicoDTO.nome() != null && !dadosAtualizacaoMedicoDTO.nome().isEmpty()) {
            nome = dadosAtualizacaoMedicoDTO.nome();
        }
        if (dadosAtualizacaoMedicoDTO.telefone() != null && !dadosAtualizacaoMedicoDTO.telefone().isEmpty()) {
            telefone = dadosAtualizacaoMedicoDTO.telefone();
        }
        if (dadosAtualizacaoMedicoDTO.endereco() != null) {
            endereco.atualizar(dadosAtualizacaoMedicoDTO.endereco());
        }
    }

    public void deletar() {
        ativo = false;
    }
}
