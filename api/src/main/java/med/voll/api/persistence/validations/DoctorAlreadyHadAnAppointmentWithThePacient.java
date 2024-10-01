package med.voll.api.persistence.validations;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DoctorAlreadyHadAnAppointmentWithThePacient implements AppointmentScheduleValidator {
    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsultaDto dados) {
        LocalDateTime inicioDaConsulta = dados.data();
        var possuiOutraConsulta = consultaRepository.findIfDoctorAlreadyHadAnAppointmentWithThePacient(dados.idMedico(),dados.idPaciente(),dados.data());
        System.out.println("Quero saber se ta passando aqui");
        System.out.println("O doctor e o " + dados.idMedico());;
        if (possuiOutraConsulta > 0) {
            throw new ValidacaoException("The doctor already had an appointment with this pacient today");
        }
        System.out.println("pacienteMarcado");
    }
}
