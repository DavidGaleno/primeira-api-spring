package med.voll.api.persistence.dto.medico;

import jakarta.validation.constraints.Pattern;
import med.voll.api.persistence.dto.DadosEnderecoDto;

public record DadosAtualizacaoMedicoDTO(String nome,
                                        @Pattern(regexp = "(\\+\\d{2})?(\\d{2})?\\d{4}-?\\d{4}") String telefone,
                                        DadosEnderecoDto endereco) {
}
