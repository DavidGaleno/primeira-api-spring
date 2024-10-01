package med.voll.api.services;

import med.voll.api.exceptions.ValidacaoException;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.models.Consulta;
import med.voll.api.persistence.models.Medico;
import med.voll.api.persistence.models.Paciente;
import med.voll.api.persistence.repository.ConsultaRepository;
import med.voll.api.persistence.repository.MedicoRepository;
import med.voll.api.persistence.repository.PacienteRepository;
import med.voll.api.persistence.validations.appointmentSchedule.AppointmentScheduleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsulta {
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    private List<AppointmentScheduleValidator> validators;

    public DadosAgendamentoConsultaDto agendar(DadosAgendamentoConsultaDto dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Paciente not found");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Medico not found");
        }
        validators.forEach(v -> v.validar(dados));
        Medico medico = escolherMedico(dados);
        System.out.println("Bora rapaz");
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        if (medico == null) {
            throw new ValidacaoException("No Doctor available at this date");
        }
        Consulta consulta = new Consulta(null, medico, paciente, true, null, dados.data(), dados.data().plusHours(1));
        consultaRepository.save(consulta);
        return new DadosAgendamentoConsultaDto(consulta);


    }

    private Medico escolherMedico(DadosAgendamentoConsultaDto dados) {
        var dataInicio = dados.data();
        var intervaloAntesDoInicio = dataInicio.minusHours(1);
        System.out.println("Especialidade vai ser " + dados.especialidade());
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade obrigatoria quando medico nao for Escolhido");
        }
        return medicoRepository.chooseRandomDoctor(dados.especialidade(), dados.data(), intervaloAntesDoInicio);


    }
}
