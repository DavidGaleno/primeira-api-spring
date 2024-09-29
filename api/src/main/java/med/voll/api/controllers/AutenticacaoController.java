package med.voll.api.controllers;

import jakarta.validation.Valid;
import med.voll.api.services.TokenService;
import med.voll.api.persistence.dto.DadosAutenticadaoDTO;
import med.voll.api.persistence.dto.DadosTokenJWT;
import med.voll.api.persistence.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticadaoDTO dadosAutenticadaoDTO) {
        UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(dadosAutenticadaoDTO.login(), dadosAutenticadaoDTO.senha());
        Authentication authentication = authenticationManager.authenticate(credentials);
        String token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }
}
