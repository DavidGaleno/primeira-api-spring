package med.voll.api.persistence.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.persistence.dto.DadosEnderecoDto;
import med.voll.api.persistence.enums.Especialidade;


public record DadosCadastroPacienteDTO(@NotBlank(message = "{nome.obrigatorio}") String nome,
                                       @NotBlank @Email String email,
                                       @NotBlank @Pattern(regexp = "(\\+\\d{2})?(\\d{2})?\\d{4}-?\\d{4}") String telefone,
                                       @NotBlank @Size(min = 11, max = 11) String cpf,
                                       @NotNull @Valid DadosEnderecoDto endereco) {
}
