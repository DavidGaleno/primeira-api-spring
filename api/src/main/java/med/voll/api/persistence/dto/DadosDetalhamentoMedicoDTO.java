package med.voll.api.persistence.dto;


import med.voll.api.persistence.enums.Especialidade;
import med.voll.api.persistence.models.Endereco;
import med.voll.api.persistence.models.Medico;

public record DadosDetalhamentoMedicoDTO(String nome, String email,
                                         String telefone,
                                         String crm,
                                         Especialidade especialidade,
                                         Endereco endereco) {

    public DadosDetalhamentoMedicoDTO(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
