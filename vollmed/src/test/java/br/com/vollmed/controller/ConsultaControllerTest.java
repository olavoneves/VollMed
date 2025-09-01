package br.com.vollmed.controller;

import br.com.vollmed.dto.DetalhesAgendamentoConsultaDTO;
import br.com.vollmed.dto.DetalhesConsultaDTO;
import br.com.vollmed.model.Especialidade;
import br.com.vollmed.service.ConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DetalhesAgendamentoConsultaDTO> detalhesAgendamentoConsultaDTOJacksonTester;

    @Autowired
    private JacksonTester<DetalhesConsultaDTO> detalhesConsultaDTOJacksonTester;

    @MockBean
    private ConsultaService consultaService;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 400, caso informações estiverem inválidas")
    @WithMockUser
    void agendarConsultaCenario01() throws Exception {
        var response = mockMvc
                .perform(post("/api/consultas"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 200, caso informações estiverem válidas")
    @WithMockUser
    void agendarConsultaCenario02() throws Exception {
        var data = LocalDate.now();
        var hora = LocalTime.now().plusHours(2);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dadosDetalhamento = new DetalhesConsultaDTO(null, 1l, 2l, data, hora);

        when(consultaService.agendarConsulta(any())).thenReturn(dadosDetalhamento);

        var jsonEsperado = detalhesConsultaDTOJacksonTester.write(
                dadosDetalhamento
        ).getJson();

        var response = mockMvc
                .perform(
                        post("/api/consultas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(detalhesAgendamentoConsultaDTOJacksonTester.write(new DetalhesAgendamentoConsultaDTO(1l, 2l, data, hora, especialidade)).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }
}