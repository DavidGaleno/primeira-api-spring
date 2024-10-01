package med.voll.api.persistence.validations;

import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;

public interface AppointmentScheduleValidator {

    void validar(DadosAgendamentoConsultaDto dados);
}
