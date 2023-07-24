package apicep.application.core.service;

import apicep.application.integracao.ApiCepComponent;
import apicep.application.integracao.BrasilApiCepComponent;
import apicep.application.integracao.ViaCepComponent;
import apicep.application.core.dto.EnderecoDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serializable;
import java.util.Optional;

@Slf4j
@Service
public class EnderecoService implements Serializable {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrasilApiCepComponent brasilApiCepComponent;

    @Autowired
    private ViaCepComponent viaCepComponent;

    @Autowired
    private ApiCepComponent apiCepComponent;

    public Optional<EnderecoDTO> buscaEnderecoPeloCep(String cep) {
        try{
            var viacepResponse = viaCepComponent.buscaCep(cep);
            var brasilcepResponse = brasilApiCepComponent.buscaCep(cep);
            var apiCepResponse = apiCepComponent.buscaCep(cep);

            if(viacepResponse.isPresent()) {
                return Optional.ofNullable(modelMapper.map(viacepResponse.get(), EnderecoDTO.class));
            } else if(brasilcepResponse.isPresent()) {
                return Optional.ofNullable(modelMapper.map(brasilcepResponse.get(), EnderecoDTO.class));
            } else if(apiCepResponse.isPresent()) {
                return Optional.ofNullable(modelMapper.map(apiCepResponse.get(), EnderecoDTO.class));
            }
        }catch (HttpStatusCodeException exception) {
            log.info("{} - CEP NÃO ENCONTRADO: {}", this.getClass().getSimpleName(), exception.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP não encontrado");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP não encontrado");
    }

}
