package com.group.holanda.apicep.service;

import com.group.holanda.apicep.constants.AppConstants;
import com.group.holanda.apicep.dto.EnderecoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;

@Service
public class CepService implements Serializable {


    public EnderecoDTO buscaEnderecoPeloCep(String cep) {
        cep = cep.replace(".", "")
                .replace("-", "");

        String url = AppConstants.URL_API_VIACEP + cep + "/json";

        try {
            RestTemplate requestGet = new RestTemplate();
            ResponseEntity<EnderecoDTO> response = requestGet.getForEntity(url, EnderecoDTO.class);

            if (HttpStatus.OK == response.getStatusCode()) {
                return response.getBody();
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP não encontrado");
        }

        return null;
    }

}
