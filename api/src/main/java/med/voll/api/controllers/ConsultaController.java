package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.repository.ConsultaRepository;
import med.voll.api.services.AgendaConsulta;
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
}
