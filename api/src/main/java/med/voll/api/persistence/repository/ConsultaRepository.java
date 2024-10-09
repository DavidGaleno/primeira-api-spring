package med.voll.api.persistence.repository;

import med.voll.api.persistence.models.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(c) FROM consultas c WHERE (c.paciente_id = :idPaciente AND :dataInicio >= c.data_inicio AND :dataInicio < c.data_fim) OR (:intervaloAntesInicio > (c.data_inicio - INTERVAL '2 HOUR') AND :dataInicio < c.data_inicio)")
    int findAppointmentsWithDateTimeForPacient(Long idPaciente,LocalDateTime dataInicio, LocalDateTime intervaloAntesInicio);

    @Query(nativeQuery = true, value = "SELECT COUNT(c) FROM consultas c WHERE c.medico_id = :idMedico AND :dataInicio >= c.data_inicio AND :dataInicio < c.data_fim OR (:intervaloAntesInicio > (c.data_inicio - INTERVAL '2 HOUR') AND :dataInicio < c.data_inicio)")
    int findAppointmentsWithDateTimeForDoctor(Long idMedico, LocalDateTime dataInicio, LocalDateTime intervaloAntesInicio);

    @Query("SELECT COUNT(c) FROM Consulta c WHERE c.medico.id = :idMedico AND c.paciente.id = :idPaciente AND DATE(CAST(c.dataInicio as timestamp)) = DATE(CAST(:dataInicio as timestamp))")
    int findIfDoctorAlreadyHadAnAppointmentWithThePacient(Long idMedico, Long idPaciente, LocalDateTime dataInicio);;
//    Page<DadosListagemConsultaDto> findByAtivoTrue(Pageable paginacao);
}
