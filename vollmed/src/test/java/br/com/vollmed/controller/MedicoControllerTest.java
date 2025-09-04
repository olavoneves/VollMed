package br.com.vollmed.controller;

import br.com.vollmed.dto.*;
import br.com.vollmed.model.Endereco;
import br.com.vollmed.model.Especialidade;
import br.com.vollmed.service.MedicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<DetalhesMedicoDTO> detalhesMedicoDTOJacksonTester;

    @Autowired
    private JacksonTester<CadastroMedicoDTO> cadastroMedicoDTOJacksonTester;

    @MockitoBean
    private MedicoService medicoService;

    @Test
    @DisplayName("Deveria devolver codigo HTTP 400, caso informações estiverem inválidas")
    @WithMockUser
    void cadastrarMedicoCenario01() throws Exception {
        var response = mockMvc
                .perform(post("/api/medicos"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo HTTP 201, caso informações estiverem válidas")
    @WithMockUser
    void cadastrarMedicoCenario02() throws Exception {
        var especialidade = Especialidade.CARDIOLOGIA;
        var enderecoDTO = new EnderecoDTO("08212340", "rua", "zl", "tatuape", "sp", "ap2");

        var dadosCadastro = new CadastroMedicoDTO(
                "Marcos", "marcos@gmail.com", "11953215425", "23452", especialidade, enderecoDTO
        );

        var dadosDetalhamento = new DetalhesMedicoDTO(
                1L,
                "Marcos",
                "marcos@gmail.com",
                "23452",
                especialidade,
                new Endereco()
        );

        when(medicoService.cadastrarMedico(any(CadastroMedicoDTO.class), any(UriComponentsBuilder.class))).thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(dadosDetalhamento));

        var jsonEsperado = detalhesMedicoDTOJacksonTester.write(dadosDetalhamento).getJson();

        var response = mockMvc.perform(
                        post("/api/medicos")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(cadastroMedicoDTOJacksonTester.write(dadosCadastro).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}
