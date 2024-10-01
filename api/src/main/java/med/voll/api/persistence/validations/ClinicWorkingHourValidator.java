package med.voll.api.persistence.validations;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicWorkingHourValidator implements AppointmentScheduleValidator {

    public void validar(DadosAgendamentoConsultaDto dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAberturaClinica = dataConsulta.getHour() < 7;
        var depoisEncerramentoClinica = dataConsulta.getHour() > 18;

        if (antesAberturaClinica | depoisEncerramentoClinica) {
            throw new ValidacaoException("Clinic is not open at this time");
        }
        if (domingo) {
            throw new ValidacaoException("Clinic is not open at this day");
        }
        System.out.println("Aberto ou nao");
    }

}
