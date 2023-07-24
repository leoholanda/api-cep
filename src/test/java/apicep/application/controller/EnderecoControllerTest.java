package apicep.application.controller;

import apicep.application.core.dto.EnderecoDTO;
import apicep.application.core.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecoService service;

    private Optional<EnderecoDTO> enderecoDTO;

    public static final String CEP = "01001-000";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enderecoDTO = Optional.of(new EnderecoDTO(
                "01001-000",
                "Praça da Sé",
                "Sé",
                "São Paulo",
                "SP",
                "",
                "3550308"));
    }

    @Test
    void quandoBuscarPorCepEntaoRetornaSucesso() {
        when(service.buscaEnderecoPeloCep(any())).thenReturn(enderecoDTO);

        ResponseEntity<EnderecoDTO> response = enderecoController.consultaEnderecoPorCep(CEP);

        assertNotNull(response);
        assertNotNull(response.getBody().getCep());
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(CEP, response.getBody().getCep());

    }

    @Test
    void quandoBuscarPorCepEntaoRetornaCepNaoEncontrado() {
        when(service.buscaEnderecoPeloCep(any())).thenReturn(Optional.empty());
        try {
            enderecoController.consultaEnderecoPorCep("000000000");
        }catch (RuntimeException e) {
            assertEquals("java.util.NoSuchElementException: No value present", e.getMessage());
        }
    }

}
