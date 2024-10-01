package med.voll.api.persistence.dto.paciente;

import med.voll.api.persistence.models.Endereco;
import med.voll.api.persistence.models.Paciente;

public record DadosDetalhamentoPacienteDTO(String nome,
                                           String email,
                                           String telefone,
                                           String cpf,
                                           Endereco endereco) {
    public DadosDetalhamentoPacienteDTO(Paciente paciente) {
        this(paciente.getNome(),paciente.getEmail(),paciente.getTelefone(),paciente.getCpf(),paciente.getEndereco());
    }
}


