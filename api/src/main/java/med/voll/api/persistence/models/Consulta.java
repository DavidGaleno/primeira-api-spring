package med.voll.api.persistence.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.dto.consulta.DadosCancelamentoConsultaDTO;
import med.voll.api.persistence.enums.MotivoCancelamentoConsulta;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private Boolean ativa;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamentoConsulta motivoCancelamento;

    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;


    public void cancelar(DadosCancelamentoConsultaDTO requisicao) {
        ativa = false;
        this.motivoCancelamento = requisicao.motivoCancelamento();
    }
}
