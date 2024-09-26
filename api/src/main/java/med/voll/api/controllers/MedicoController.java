package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.persistence.dto.DadosAtualizacaoMedicoDTO;
import med.voll.api.persistence.dto.DadosCadastroMedicoDTO;
import med.voll.api.persistence.dto.DadosDetalhamentoMedicoDTO;
import med.voll.api.persistence.dto.DadosListagemMedicoDTO;
import med.voll.api.persistence.models.Medico;
import med.voll.api.persistence.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dadosCadastroMedicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = new Medico(dadosCadastroMedicoDTO);
        repository.save(medico);
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        DadosDetalhamentoMedicoDTO dadosDetalhamentoMedicoDTO = new DadosDetalhamentoMedicoDTO(medico);
        return ResponseEntity.created(uri).body(dadosDetalhamentoMedicoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDTO>> DadosListagemMedicoDTO(@PageableDefault(size = 5, page = 0, direction = Sort.Direction.ASC) Pageable paginacao) {
        return ResponseEntity.ok(repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedicoDTO> DadosListagemMedicoDTO(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        DadosDetalhamentoMedicoDTO dadosDetalhamentoMedicoDTO = new DadosDetalhamentoMedicoDTO(medico);
        return ResponseEntity.ok(dadosDetalhamentoMedicoDTO);


    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedicoDTO> atualizar(@RequestBody @Valid DadosAtualizacaoMedicoDTO dadosAtualizacaoMedicoDTO, @PathVariable("id") Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.atualizar(dadosAtualizacaoMedicoDTO);
        DadosDetalhamentoMedicoDTO dadosDetalhamentoMedicoDTO = new DadosDetalhamentoMedicoDTO(medico);
        return ResponseEntity.ok(dadosDetalhamentoMedicoDTO);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
//        Medico medico = repository.getReferenceById(id);
//        repository.delete(medico);
        Medico medico = repository.getReferenceById(id);
        medico.deletar();
        return ResponseEntity.noContent().build();
    }
}
