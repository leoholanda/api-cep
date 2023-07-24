package apicep.application.integracao;

import apicep.application.integracao.response.CepResponse;
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
public class ApiCepComponent {

    @Value("${api.url.apicep}")
    private String API_URL;

    public Optional<CepResponse> buscaCep(String cep) {
        if(cep.length() > 5) {
            cep = cep.replace(".", "")
                    .replace("-", "");
            cep = cep.substring(0, 5) + "-" + cep.substring(5);
        }

        String url = this.API_URL + cep + ".json";

        try {
            log.info("{} - BUSCANDO CEP APICEP", this.getClass().getSimpleName());
            RestTemplate requestGet = new RestTemplate();
            ResponseEntity<CepResponse> response = requestGet.getForEntity(url, CepResponse.class);

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
