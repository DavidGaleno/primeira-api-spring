package med.voll.api.controllers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.persistence.dto.medico.DadosAtualizacaoMedicoDTO;
import med.voll.api.persistence.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.persistence.dto.medico.DadosDetalhamentoMedicoDTO;
import med.voll.api.persistence.dto.medico.DadosListagemMedicoDTO;
import med.voll.api.persistence.models.Medico;
import med.voll.api.persistence.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
@OpenAPIDefinition(
        tags = {@Tag(name = "Medicos", description = "Operations related to Medicos management")})
public class MedicoController {

    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    @Operation(description = "Cadastra Medicos",tags = "Medicos")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dadosCadastroMedicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = new Medico(dadosCadastroMedicoDTO);
        repository.save(medico);
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        DadosDetalhamentoMedicoDTO dadosDetalhamentoMedicoDTO = new DadosDetalhamentoMedicoDTO(medico);
        return ResponseEntity.created(uri).body(dadosDetalhamentoMedicoDTO);
    }

    @Operation(description = "Busca Medicos",tags = "Medicos")
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> listarMedicos(Pageable paginacao) {
        return ResponseEntity.ok(repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new));
    }

    @Operation(description = "Busca Medico por Id",tags = "Medicos")
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> detalharMedico(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        DadosDetalhamentoMedicoDTO dadosDetalhamentoMedicoDTO = new DadosDetalhamentoMedicoDTO(medico);
        return ResponseEntity.ok(dadosDetalhamentoMedicoDTO);


    }

    @PatchMapping("/{id}")
    @Operation(description = "Atualiza Medico",tags = "Medicos")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO, @PathVariable("id") Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.atualizar(dadosAtualizacaoMedicoDTO);
        DadosDetalhamentoMedicoDTO dadosDetalhamentoMedicoDTO = new DadosDetalhamentoMedicoDTO(medico);
        return ResponseEntity.ok(dadosDetalhamentoMedicoDTO);

    }

    @DeleteMapping("/{id}")
    @Operation(description = "Exclui Medico", summary = "Exclui Medico",tags = "Medicos")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.deletar();
        return ResponseEntity.noContent().build();
    }
}
