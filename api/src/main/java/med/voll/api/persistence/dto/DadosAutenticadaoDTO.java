package med.voll.api.persistence.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosAutenticadaoDTO(@NotBlank String login, @NotBlank String senha) {
}
