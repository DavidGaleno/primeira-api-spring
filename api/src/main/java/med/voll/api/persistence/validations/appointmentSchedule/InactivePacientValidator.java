package med.voll.api.persistence.validations.appointmentSchedule;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InactivePacientValidator implements AppointmentScheduleValidator {
    @Autowired
    PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoConsultaDto dados) {
        var inactivePacient = pacienteRepository.findAtivoById(dados.idPaciente());
        if (!inactivePacient) throw new ValidacaoException("Inactive Pacient");
        System.out.println("Paciente Inativo");
    }
}
