package med.voll.api.persistence.validations.appointmentCancelation;

import med.voll.api.persistence.dto.consulta.DadosCancelamentoConsultaDTO;

public interface AppointmentCancelationValidation {

    void validar(Long id);
}
