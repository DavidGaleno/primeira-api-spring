package med.voll.api.persistence.repository;

import med.voll.api.persistence.enums.Especialidade;
import med.voll.api.persistence.models.Medico;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(nativeQuery = true,value = "SELECT * FROM medicos m WHERE m.ativo = true AND m.especialidade = :especialidade AND m.id NOT IN (SELECT c.medico_id FROM consultas c WHERE :dataInicio >= c.data_inicio AND :dataInicio < c.data_fim OR :intervaloAntesInicio > (c.data_inicio - INTERVAL '2 HOUR') ) order by random() limit 1")
    Medico chooseRandomDoctor(Especialidade especialidade, LocalDateTime dataInicio, LocalDateTime intervaloAntesInicio);

    @Query("SELECT m.ativo FROM Medico m WHERE m.id = :id")
    Boolean findAtivoById(Long id);
}
