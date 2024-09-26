package med.voll.api.persistence.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.persistence.dto.DadosEnderecoDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEnderecoDto dadosEnderecoDto) {
        logradouro = dadosEnderecoDto.logradouro();
        bairro = dadosEnderecoDto.bairro();
        cep = dadosEnderecoDto.cep();
        numero = dadosEnderecoDto.numero();
        complemento = dadosEnderecoDto.complemento();
        cidade = dadosEnderecoDto.cidade();
        uf = dadosEnderecoDto.uf();
    }

    public void atualizar(DadosEnderecoDto dadosEnderecoDto) {
        if (dadosEnderecoDto.logradouro() != null && dadosEnderecoDto.logradouro().isEmpty()) {
            logradouro = dadosEnderecoDto.logradouro();
        }
        if (dadosEnderecoDto.bairro() != null && dadosEnderecoDto.bairro().isEmpty()) {
            bairro = dadosEnderecoDto.bairro();
        }
        if (dadosEnderecoDto.cep() != null && dadosEnderecoDto.cep().isEmpty()) {
            cep = dadosEnderecoDto.cep();
        }
        if (dadosEnderecoDto.cidade() != null && dadosEnderecoDto.cidade().isEmpty()) {
            cidade = dadosEnderecoDto.cidade();
        }
        if (dadosEnderecoDto.uf() != null && dadosEnderecoDto.uf().isEmpty()) {
            uf = dadosEnderecoDto.uf();
        }
    }
}
