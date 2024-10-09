package med.voll.api.persistence.dto.medico;

import med.voll.api.persistence.enums.Especialidade;
import med.voll.api.persistence.models.Medico;

public record DadosListagemMedicoDTO(Long id, String nome, String email, String crm,  Especialidade especialidade) {
    public DadosListagemMedicoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(),
                medico.getEmail(),
                medico.getCrm(),
                medico.getEspecialidade());
    }
}
