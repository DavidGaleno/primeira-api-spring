package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.dto.consulta.DadosCancelamentoConsultaDTO;
import med.voll.api.persistence.models.Consulta;
import med.voll.api.persistence.repository.ConsultaRepository;
import med.voll.api.services.AgendaConsulta;
import med.voll.api.services.CancelaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    AgendaConsulta agendaConsulta;
    @Autowired
    CancelaConsulta cancelaConsulta;
//    @GetMapping
//    public ResponseEntity<Page<DadosListagemConsultaDto>> listarMedicos(Pageable paginacao) {
//        return ResponseEntity.ok(repository.findByAtivoTrue(paginacao).map(DadosListagemConsultaDTO::new));
//    }

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDto requisicao) {
        var consulta = agendaConsulta.agendar(requisicao);
        return ResponseEntity.ok().body(consulta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity cancelar(@PathVariable("id") Long id, @RequestBody @Valid DadosCancelamentoConsultaDTO requisicao) {
        cancelaConsulta.cancelar(id, requisicao);
        return ResponseEntity.noContent().build();
    }

}
