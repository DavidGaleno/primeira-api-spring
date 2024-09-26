package med.voll.api.persistence.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.persistence.enums.Especialidade;

public record DadosCadastroMedicoDTO(@NotBlank(message = "{nome.obrigatorio}") String nome, @NotBlank @Email String email,
                                     @NotBlank @Pattern(regexp = "(\\+\\d{2})?(\\d{2})?\\d{4}-?\\d{4}") String telefone,
                                     @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
                                     @NotNull Especialidade especialidade,
                                     @NotNull @Valid DadosEnderecoDto endereco) {
}
