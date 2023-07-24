package apicep.application.service;

import apicep.application.core.dto.EnderecoDTO;
import apicep.application.core.service.EnderecoService;
import apicep.application.integracao.ApiCepComponent;
import apicep.application.integracao.BrasilApiCepComponent;
import apicep.application.integracao.ViaCepComponent;
import apicep.application.integracao.response.BrasilCepResponse;
import apicep.application.integracao.response.CepResponse;
import apicep.application.integracao.response.ViaCepResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
public class EnderecoServiceTest {

    @Autowired
    private EnderecoService service;

    @Mock
    private ViaCepComponent viaCep;
    @Mock
    private ApiCepComponent apiCep;
    @Mock
    private BrasilApiCepComponent brasilApi;

    @Mock
    private ModelMapper modelMapper;

    private Optional<EnderecoDTO> enderecoDTO;
    private Optional<ViaCepResponse> viaCepResponse;
    private Optional<BrasilCepResponse> brasilCepResponse;
    private Optional<CepResponse> apiCepResponse;
    public static final String CEP = "01001000";
    public static final String CEP_INVALIDO = "00000000";

    public static final String OBJETO_NAO_ENCONTRADO = "CEP não encontrado";

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

        viaCepResponse = Optional.of(new ViaCepResponse(CEP, enderecoDTO.get().getLogradouro(),
                enderecoDTO.get().getBairro(), enderecoDTO.get().getCidade(),
                enderecoDTO.get().getEstado(), enderecoDTO.get().getCodigoIbgeCidade()));

        brasilCepResponse = Optional.of(new BrasilCepResponse(CEP, enderecoDTO.get().getLogradouro(),
                enderecoDTO.get().getBairro(), enderecoDTO.get().getCidade(),
                enderecoDTO.get().getEstado()));

        apiCepResponse = Optional.of(new CepResponse(CEP, enderecoDTO.get().getLogradouro(),
                enderecoDTO.get().getBairro(), enderecoDTO.get().getCidade(),
                enderecoDTO.get().getEstado(), 0));

    }

    @Test
    void quandoBuscarNaApiViaCepEntaoRetornaInstanciaDeEndereco() {
        when(viaCep.buscaCep(any())).thenReturn(viaCepResponse);

        var response = service.buscaEnderecoPeloCep(CEP);
        assertNotNull(response.get());
        assertTrue(response.isPresent());
        assertEquals(EnderecoDTO.class, response.get().getClass());
        assertEquals(enderecoDTO.get().getLogradouro(), response.get().getLogradouro());
    }

    @Test
    void quandoBuscarNaApiBrasilCepEntaoRetornaInstanciaDeEndereco() {
        when(brasilApi.buscaCep(any())).thenReturn(brasilCepResponse);

        var response = service.buscaEnderecoPeloCep(CEP);
        assertNotNull(response.get());
        assertTrue(response.isPresent());
        assertEquals(EnderecoDTO.class, response.get().getClass());
        assertEquals(enderecoDTO.get().getLogradouro(), response.get().getLogradouro());
    }

    @Test
    void quandoBuscarNaApiCepEntaoRetornaInstanciaDeEndereco() {
        when(apiCep.buscaCep(any())).thenReturn(apiCepResponse);

        var response = service.buscaEnderecoPeloCep(CEP);
        assertNotNull(response.get());
        assertTrue(response.isPresent());
        assertEquals(EnderecoDTO.class, response.get().getClass());
        assertEquals(enderecoDTO.get().getLogradouro(), response.get().getLogradouro());
    }

}
