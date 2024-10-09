package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.persistence.dto.medico.DadosDetalhamentoMedicoDTO;
import med.voll.api.persistence.dto.paciente.DadosAtualizacoPacienteDTO;
import med.voll.api.persistence.dto.paciente.DadosCadastroPacienteDTO;
import med.voll.api.persistence.dto.paciente.DadosDetalhamentoPacienteDTO;
import med.voll.api.persistence.dto.paciente.DadosListagemPacienteDTO;
import med.voll.api.persistence.models.Paciente;
import med.voll.api.persistence.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
    @Autowired
    PacienteRepository repository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemPacienteDTO>> listarPacientes(Pageable paginacao) {
        return ResponseEntity.ok(repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacienteDTO::new));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDTO> cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO requisicao, UriComponentsBuilder uriBuilder) {
        Paciente paciente = new Paciente(requisicao);
        repository.save(paciente);
        URI uri = uriBuilder.buildAndExpand(paciente.getId()).toUri();
        DadosDetalhamentoPacienteDTO pacienteCadastrado = new DadosDetalhamentoPacienteDTO(paciente);
        return ResponseEntity.created(uri).body(pacienteCadastrado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoPacienteDTO> detalharPaciente(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        DadosDetalhamentoPacienteDTO pacienteDTO = new DadosDetalhamentoPacienteDTO(paciente);
        return ResponseEntity.ok(pacienteDTO);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDTO> atualizar(@RequestBody @Valid DadosAtualizacoPacienteDTO requisicao, @PathVariable("id") Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.atualizar(requisicao);
        DadosDetalhamentoPacienteDTO pacienteAtualizado = new DadosDetalhamentoPacienteDTO(paciente);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoPacienteDTO> deletar(@PathVariable("id") Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.deletar();
        return ResponseEntity.noContent().build();
    }


}
