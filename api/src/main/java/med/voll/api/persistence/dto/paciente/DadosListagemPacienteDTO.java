package med.voll.api.persistence.dto.paciente;

import med.voll.api.persistence.models.Medico;
import med.voll.api.persistence.models.Paciente;

public record DadosListagemPacienteDTO(Long id, String nome, String email, String cpf) {
    public DadosListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf());
    }

}
