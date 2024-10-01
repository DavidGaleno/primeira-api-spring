package med.voll.api.persistence.validations;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FindDoctorWithAnotherAppointmentValidator implements AppointmentScheduleValidator {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultaDto dados) {
        LocalDateTime inicioDaConsulta = dados.data();
        LocalDateTime intervaloAntesInicio = inicioDaConsulta.minusHours(1);
        if (dados.idMedico() == null) return;
        var possuiOutraConsulta = consultaRepository.findAppointmentsWithDateTimeForDoctor(dados.idMedico(), inicioDaConsulta, intervaloAntesInicio);
        if (possuiOutraConsulta > 0) {
            throw new ValidacaoException("The doctor already has an appointment in the time informed");
        }
        System.out.println("Doutor tem outra consulta");

    }
}

