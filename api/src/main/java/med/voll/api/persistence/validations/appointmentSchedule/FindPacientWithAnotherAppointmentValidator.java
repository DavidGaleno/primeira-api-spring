package med.voll.api.persistence.validations.appointmentSchedule;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FindPacientWithAnotherAppointmentValidator implements AppointmentScheduleValidator {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultaDto dados) {
        LocalDateTime inicioDaConsulta = dados.data();
        LocalDateTime intervaloAntesInicio = inicioDaConsulta.minusHours(1);
        System.out.println(inicioDaConsulta);
        var possuiOutraConsulta = consultaRepository.findAppointmentsWithDateTimeForPacient(dados.idPaciente(), inicioDaConsulta,intervaloAntesInicio);
        System.out.println("outras consultas " +possuiOutraConsulta );
        if (possuiOutraConsulta > 0) {
            throw new ValidacaoException("There is a conflict with other appointment");
        }
        System.out.println("pacienteMarcado");
    }
}
