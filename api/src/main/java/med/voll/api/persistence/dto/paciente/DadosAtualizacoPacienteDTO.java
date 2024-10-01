package med.voll.api.persistence.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.persistence.dto.DadosEnderecoDto;

public record DadosAtualizacoPacienteDTO(@NotBlank(message = "{nome.obrigatorio}") String nome,
                                         @NotBlank @Pattern(regexp = "(\\+\\d{2})?(\\d{2})?\\d{4}-?\\d{4}") String telefone,
                                         @NotNull @Valid DadosEnderecoDto endereco) {
}