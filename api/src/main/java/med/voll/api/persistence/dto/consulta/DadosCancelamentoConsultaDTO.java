package med.voll.api.persistence.dto.consulta;


import jakarta.validation.constraints.NotNull;
import med.voll.api.persistence.enums.MotivoCancelamentoConsulta;

public record DadosCancelamentoConsultaDTO(@NotNull
                                           MotivoCancelamentoConsulta motivoCancelamento) {
}
