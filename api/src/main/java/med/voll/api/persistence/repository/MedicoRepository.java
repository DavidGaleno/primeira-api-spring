package med.voll.api.persistence.repository;

import med.voll.api.persistence.models.Medico;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;


public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    Page<Medico> findAllByAtivoFalse(Pageable paginacao);
}
