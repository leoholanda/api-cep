package apicep.application.integracao;

import apicep.application.core.dto.EnderecoDTO;
import apicep.application.integracao.response.ViaCepResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
public class ViaCepComponent {

    @Value("${api.url.viacep}")
    private String API_URL;

    public Optional<ViaCepResponse> buscaCep(String cep) {
        cep = cep.replace(".", "")
                .replace("-", "");

        String url = API_URL + cep + "/json";

        try {
            log.info("{} - BUSCANDO CEP API VIACEP", this.getClass().getSimpleName());
            RestTemplate requestGet = new RestTemplate();
            Optional<ResponseEntity<ViaCepResponse>> response = Optional.of(requestGet.getForEntity(url, ViaCepResponse.class));

            if (HttpStatus.OK == response.get().getStatusCode()) {
                return Optional.ofNullable(response.get().getBody());
            } else {
                return Optional.empty();
            }

        } catch (HttpStatusCodeException e) {
            log.info("{} - CEP NÃO ENCONTRADO: {}", this.getClass().getSimpleName(), e.getMessage());
            return Optional.empty();
        }
    }
}
