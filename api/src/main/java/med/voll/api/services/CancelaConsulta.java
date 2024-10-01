package med.voll.api.services;

import jakarta.transaction.Transactional;
import med.voll.api.persistence.dto.consulta.DadosCancelamentoConsultaDTO;
import med.voll.api.persistence.models.Consulta;
import med.voll.api.persistence.repository.ConsultaRepository;
import med.voll.api.persistence.validations.appointmentCancelation.AppointmentCancelationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelaConsulta {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    private List<AppointmentCancelationValidation> validators;

    @Transactional
    public void cancelar(Long id, DadosCancelamentoConsultaDTO requisicao) {
        Consulta consulta = consultaRepository.getReferenceById(id);

        validators.forEach(v -> v.validar(id));

        consulta.cancelar(requisicao);
    }
}
