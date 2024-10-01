package med.voll.api.persistence.validations.appointmentCancelation;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosCancelamentoConsultaDTO;
import med.voll.api.persistence.models.Consulta;
import med.voll.api.persistence.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelationMustBeAtLeast24HoursBeforeValidator implements AppointmentCancelationValidation {

    @Autowired
    ConsultaRepository repository;

    @Override
    public void validar(Long id) {
        Consulta consulta = repository.getReferenceById(id);
        var agora = LocalDateTime.of(2024, 10, 29, 10, 0);
        var is24HoursBefore = Duration.between(consulta.getDataInicio(), agora).toHours() >= 24;

        if (!is24HoursBefore) throw new ValidacaoException("An appointment must be cancelled at least 24 hours early");

    }
}
