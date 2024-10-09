package med.voll.api.persistence.repository;

import jakarta.persistence.EntityManager;
import med.voll.api.persistence.dto.DadosEnderecoDto;
import med.voll.api.persistence.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.persistence.dto.paciente.DadosCadastroPacienteDTO;
import med.voll.api.persistence.enums.Especialidade;
import med.voll.api.persistence.enums.MotivoCancelamentoConsulta;
import med.voll.api.persistence.models.Consulta;
import med.voll.api.persistence.models.Medico;
import med.voll.api.persistence.models.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private EntityManager em;

    private Consulta cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        Consulta consulta = new Consulta(null, medico, paciente, true, MotivoCancelamentoConsulta.MedicoCancelou, data, data.plusHours(1));
        em.persist(consulta);
        return consulta;
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedicoDTO dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedicoDTO(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPacienteDTO dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPacienteDTO(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEnderecoDto dadosEndereco() {
        return new DadosEnderecoDto(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

    @Test
    void shouldNOTchooseRandomDoctorWhenThereIsNoDoctorAtTheDateInformed() {
//        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@voll.med", "00000000");
        cadastrarConsulta(medico, paciente, LocalDateTime.of(2024, 10, 8, 12, 0,0));

        var medicoLivre = medicoRepository.chooseRandomDoctor(String.valueOf(Especialidade.CARDIOLOGIA), LocalDateTime.of(2024, 10, 8, 12, 0,0), LocalDateTime.of(2024, 10, 8, 11, 0,0));
        assertThat(medicoLivre).isNull();

    }

    @Test
    void shouldNOTchooseRandomDoctorWhenTheyAreFree() {
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var medicoLivre = medicoRepository.chooseRandomDoctor(String.valueOf(Especialidade.CARDIOLOGIA), LocalDateTime.of(2024, 10, 8, 12, 0), LocalDateTime.of(2024, 10, 8, 11, 0));
        assertThat(medicoLivre).isEqualTo(medico);

    }
}