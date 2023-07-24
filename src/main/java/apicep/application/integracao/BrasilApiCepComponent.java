package apicep.application.integracao;

import apicep.application.integracao.response.BrasilCepResponse;
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
public class BrasilApiCepComponent {

    @Value("${api.url.brasilcep}")
    private String url;

    public Optional<BrasilCepResponse> buscaCep(String cep) {
        cep = cep.replace(".", "").replace("-", "");

        String url = this.url + cep;

        try {
            log.info("{} - BUSCANDO CEP API BRASILCEP", this.getClass().getSimpleName());
            RestTemplate requestGet = new RestTemplate();
            ResponseEntity<BrasilCepResponse> response = requestGet.getForEntity(url, BrasilCepResponse.class);

            if (HttpStatus.OK == response.getStatusCode()) {
                return Optional.ofNullable(response.getBody());
            }

        } catch (HttpStatusCodeException exception) {
            log.info("{} - CEP NÃO ENCONTRADO: {}", this.getClass().getSimpleName(), exception.getMessage());
            return Optional.empty();
        }

        return Optional.empty();
    }
}
