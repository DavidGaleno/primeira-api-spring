package med.voll.api.persistence.validations.appointmentSchedule;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class EarlyArrivalTimeValidator implements  AppointmentScheduleValidator {
    public void validar(DadosAgendamentoConsultaDto dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();

        var isLate = Duration.between(agora, dataConsulta).toMinutes() < 30;
        if (isLate) {
            throw new ValidacaoException("The appointment must be scheduled 30 minutes before");
        }
        System.out.println("Chegou cedo");

    }

}
