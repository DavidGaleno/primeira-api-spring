package med.voll.api.persistence.validations.appointmentSchedule;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InativeDoctorValidator implements AppointmentScheduleValidator {
    @Autowired
    MedicoRepository repository;

    public void validar(DadosAgendamentoConsultaDto dados) {
        System.out.println(dados.idMedico());
        if (dados.idMedico() == null) return;
        System.out.println("Sera que foi");
        var inactiveDoctor = repository.findAtivoById(dados.idMedico());
        if (!inactiveDoctor) throw new ValidacaoException("Inactive Doctor");
    }


}
