package med.voll.api.persistence.dto.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.persistence.enums.Especialidade;
import med.voll.api.persistence.models.Consulta;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultaDto(@JsonAlias("medico_id")
                                          Long idMedico, @NotNull @JsonAlias("paciente_id")
                                          Long idPaciente, Especialidade especialidade,
                                          @NotNull @Future LocalDateTime data) {
    public DadosAgendamentoConsultaDto(Consulta consulta) {
        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getMedico().getEspecialidade(), consulta.getDataInicio());
    }
}
