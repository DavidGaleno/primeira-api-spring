package med.voll.api.persistence.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoMedicoDTO(String nome,
                                        @Pattern(regexp = "(\\+\\d{2})?(\\d{2})?\\d{4}-?\\d{4}") String telefone,
                                        DadosEnderecoDto endereco) {
}
