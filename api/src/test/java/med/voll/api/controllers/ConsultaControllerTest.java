package med.voll.api.controllers;

import med.voll.api.persistence.dto.consulta.DadosAgendamentoConsultaDto;
import med.voll.api.persistence.dto.consulta.DadosDetalhamentoConsultaDto;
import med.voll.api.persistence.enums.Especialidade;
import med.voll.api.services.AgendaConsulta;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosAgendamentoConsultaDto> dadosAgendamentoConsultaDtoJacksonTester;

    @MockBean
    private AgendaConsulta agendaConsulta;

    @Test
    @WithMockUser
    void deveRetornarBadRequestQUANDOInformacoesInvalidas() throws Exception {
        var response = mvc.perform(post("/consultas")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @WithMockUser
    void deveRetornarOKQUANDOInformacoesValidas() throws Exception {
        var consulta = new DadosAgendamentoConsultaDto(null, 1L, Especialidade.ORTOPEDIA, LocalDateTime.now().plusHours(1));
        var respostaEsperada = dadosAgendamentoConsultaDtoJacksonTester.write(consulta).getJson();
        BDDMockito.given(agendaConsulta.agendar(any())).willReturn(consulta);

        var response = mvc.perform(post("/consultas").contentType(MediaType.APPLICATION_JSON).content(dadosAgendamentoConsultaDtoJacksonTester.write(consulta).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(respostaEsperada);

    }
}